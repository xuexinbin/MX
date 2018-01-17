package com.mx.common.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mx.common.util.CommonUtil;

/**
 * 插入sqlbean
 * @author xiexinbin
 *
 */
public class InsertDataBean {
	/** 表名 */
	private String tableId;
	
	/** 插入sql文 */
	private String sql;
	
	public InsertDataBean() {
		super();
	}

	/**
	 * 构造insert多行sql
	 * 
	 * @param tableId 表名
	 * @param insertData json格式字符串
	 * 例:
	 	demo_article：demo_article
	 	insertData：[{"title":"456","keyword":"add","author":"bbb","recommend":0,"top":0},{"title":"123","keyword":"add","author":"bbb","recommend":0,"top":0}]
		INSERT INTO (top,author,recommend,title,keyword) VALUES (0,'bbb',0,'123456','add'),(0,'bbb',0,'123','add');
	 */
	public InsertDataBean(String tableId, String insertData) {
		this.tableId = tableId;
		if (CommonUtil.isEmpty(insertData)) {
			return;
		}
		
		JSONArray jsonArray = JSONObject.parseArray(insertData);
		Iterator<Object> it = jsonArray.iterator();
		String insertKey = "";
		ArrayList<String> insertDataArr = new ArrayList<String>();
		while (it.hasNext()) {
			JSONObject obj = (JSONObject) it.next();
			
			Set<String> keySet = obj.keySet();
			insertKey = "".equals(insertKey) ? CommonUtil.join(keySet) : insertKey;
			insertDataArr.add(CommonUtil.insertDataJoin(obj.values().toArray()));
		}
		this.sql = "INSERT INTO " + tableId + "(" +  CommonUtil.convertStringToDB(insertKey) + ") VALUES " + CommonUtil.join(insertDataArr) + ";";
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
