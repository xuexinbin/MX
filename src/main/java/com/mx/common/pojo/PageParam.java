package com.mx.common.pojo;

import com.alibaba.fastjson.JSONObject;
import com.mx.common.util.CommonUtil;

/**
 * 分页参数bean
 * @author xiexinbin
 *
 */
public class PageParam {
	/** 页码 */
	private int pageNumber;
	
	/** 每页显示数量 */
	private int pageSize;
	
	/** bootstrap-table: 搜索内容 */
	private String search;
	
	/** bootstrap-table: 排序 */
	private String sortOrder;
	
	/** bootstrap-table: 排序  -> set方法重写驼峰转为数据库类型*/
	private String sortName;
	
	public PageParam() {
		super();
	}

	/**
	 * bootstrap-table参数构造函数
	 * @param JSONObject 
	 * 		limit:每页显示数量
	 * 		offset:开始行数 第一行:0
	 * 		search:查找内容
	 * 		sort:排序列
	 * 		order:asc升序 desc降序
	 */
	public PageParam(JSONObject obj) {
		super();
		this.pageSize = obj.getIntValue("limit");
		int offset = obj.getIntValue("offset");
		this.pageNumber = offset / pageSize + 1;
		this.search = obj.getString("search");
		this.sortOrder = obj.getString("sort");
		this.sortName = CommonUtil.convertStringToDB(obj.getString("order"));
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = CommonUtil.convertStringToDB(sortName);
	}

	
}
