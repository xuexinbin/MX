package com.mx.common.pojo;

/**
 * 表格保存参数
 * @author xiexinbin
 *
 */
public class TableParam {
	
	/** 更新内容 */
	private String updateStr;
	/** 删除内容 */
	private String delStr;
	/** 新增内容 */
	private String insertStr;
	/** 表格唯一主键 */
	private String uniqueId;
	/** 表格名称 */
	private String tableName;
	
	public String getUpdateStr() {
		return updateStr;
	}
	public void setUpdateStr(String updateStr) {
		this.updateStr = updateStr;
	}
	public String getDelStr() {
		return delStr;
	}
	public void setDelStr(String delStr) {
		this.delStr = delStr;
	}
	public String getInsertStr() {
		return insertStr;
	}
	public void setInsertStr(String insertStr) {
		this.insertStr = insertStr;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
