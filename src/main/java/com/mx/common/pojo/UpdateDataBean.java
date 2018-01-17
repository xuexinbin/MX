package com.mx.common.pojo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mx.common.util.CommonUtil;

/**
 * 多行更新ben
 * 前提: where后只能有一个条件
 * @author xiexinbin
 *
 */
public class UpdateDataBean {
	/** 表名 */
	private String tableId;
	
	/** 条件列 */
	private String conditionCol;
	
	/** 更新sql文 */
	private String sql;
	
	public UpdateDataBean() {
		super();
	}

	/**
	 * 构造同时更新多行sql -> update性能优化
	 * 
	 * @param tableId 表名
	 * @param conditionCol 条件列名(驼峰 -> 和数据加载的Bean相同)
	 * @param updateData json格式字符串
	 * 例:
	 	demo_article：demo_article
	 	conditionCol： conditionCol
	 	updateData： [{"articleId":1,"keyword":"2","title":"第一111"},{"articleId":2,"title":"第二篇222"}]
		UPDATE demo_article SET keyword = CASE article_id WHEN '1' THEN '3' ELSE keyword END , title = CASE article_id WHEN '1' THEN '第一11' WHEN '2' THEN '第二篇22' ELSE tittle END WHERE article_id IN ('1','2');
	 */
	public UpdateDataBean(String tableId, String conditionCol, String updateData) {
		this.tableId = tableId;
		this.conditionCol = conditionCol;
		if (CommonUtil.isEmpty(updateData)) {
			return;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE " + tableId);
		String conditionStr = "";
		
		// 构造sql参数 形式为HashMap<"更新的列", HashMap<"条件/主键的值", "更新列对应主键的值">> 
		JSONArray jsonArray = JSONObject.parseArray(updateData);
		Iterator<Object> it = jsonArray.iterator();
		HashMap<String, HashMap<String, String>> conMap = new HashMap<String, HashMap<String, String>>();
		while (it.hasNext()) {
			JSONObject obj = (JSONObject) it.next();
			
			Set<String> keySet = obj.keySet();
			for (String key : keySet) {
				if (conditionCol.equals(key)) {
					continue;
				}
				HashMap<String, String> map = conMap.get(key);
				if (map == null) {
					HashMap<String, String> newMap = new HashMap<String, String>();
					
					newMap.put(String.valueOf(obj.get(conditionCol)), String.valueOf(obj.get(key)));
					conMap.put(key, newMap);
				} else {
					map.put(String.valueOf(obj.get(conditionCol)), String.valueOf(obj.get(key)));
				}
			}
			conditionStr += "'" + String.valueOf(obj.get(conditionCol)) + "',";
		}
		
		// 条件转为DB形式
		conditionCol = CommonUtil.convertStringToDB(conditionCol);
		
		Iterator<Entry<String, HashMap<String, String>>> iter = conMap.entrySet().iterator();
		String setStr = " SET ";
		while (iter.hasNext()) {
			Entry<String, HashMap<String, String>> entry = iter.next();
			// 需要更新的列
			String tempColumn = CommonUtil.convertStringToDB(String.valueOf(entry.getKey()));
			sb.append(setStr + tempColumn + " = CASE " + conditionCol);
			
			Iterator<Entry<String, String>> caseMap = entry.getValue().entrySet().iterator();
			while (caseMap.hasNext()) {
				Entry<String, String> caseMapData = caseMap.next();
				sb.append(" WHEN '" + caseMapData.getKey() + "' THEN '" + caseMapData.getValue() + "'");
			}
			sb.append(" ELSE " + tempColumn);
			sb.append(" END ");
			setStr = ", ";
		}
		sb.append("WHERE "+ conditionCol + " IN (" + conditionStr.substring(0, conditionStr.length()-1) + ");");
		
		this.sql = sb.toString();
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getConditionCol() {
		return conditionCol;
	}

	public void setConditionCol(String conditionCol) {
		this.conditionCol = conditionCol;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
