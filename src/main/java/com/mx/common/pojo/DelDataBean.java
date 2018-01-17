package com.mx.common.pojo;

import com.mx.common.util.CommonUtil;

/**
 * delete 共通方法
 * 前提: where后只能有一个条件
 *
 * @author mx
 */
public class DelDataBean {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 条件列
     */
    private String conditionCol;

    /**
     * 删除sql文
     */
    private String sql;

    /**
     * 构造同时删除多行sql
     *
     * @param tableId      表名
     * @param conditionCol 条件列名(驼峰 -> 和数据加载的Bean相同)
     * @param delData      字符串 ('1','2','3')
     */
    public DelDataBean(String tableId, String conditionCol, String delData) {
        conditionCol = CommonUtil.convertStringToDB(conditionCol);
        this.sql = "DELETE FROM " + tableId + " WHERE " + conditionCol + " IN" + delData + ";";
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
