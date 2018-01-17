package com.mx.common.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型转换 工具类
 * @author xiexinbin
 *
 */
public class DataUtil {

	/**
	 * 将list数据转为分页表格json数据
	 * @param list
	 * @return
	 */
	public static String convertListToTablejson(List list) {
		PageInfo pageInfo = new PageInfo(list);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", pageInfo.getTotal());  
		resultMap.put("rows", pageInfo.getList());  
		return JSON.toJSON(resultMap).toString();
	}
	
	/**
	 * 将list数据转为分页表格map
	 * @param list
	 * @return
	 */
	public static Map<String, Object> convertListToTableMap(List list) {
		PageInfo pageInfo = new PageInfo(list);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", pageInfo.getTotal());  
		resultMap.put("rows", pageInfo.getList());  
		return resultMap;
	}
	
	
}
