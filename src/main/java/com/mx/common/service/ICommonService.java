package com.mx.common.service;

import com.mx.common.pojo.SelectBean;
import com.mx.common.pojo.TableParam;

import java.util.List;

public interface ICommonService {

    /**
     * 更新多行数据
     *
     * @param tableId      表名
     * @param conditionCol 条件列
     * @param updateData   更新数据
     * @return int
     */
    int updateRows(String tableId, String conditionCol, String updateData);

    /**
     * 保存表格数据
     *
     * @param tableParam
     */
    void saveTableData(TableParam tableParam);

    /**
     * 批量插入操作
     *
     * @param tableId
     * @param insertData json格式字符串 ->InsertDataBean
     * @return
     */
    int insertRows(String tableId, String insertData);

    /**
     * 删除表数据
     *
     * @param tableName 表名
     * @param column    条件列名(和表字段相同): 一个条件
     * @param key       字符串(逗号拼接1,2,3)
     * @return int
     */
    int deleteRows(String tableName, String column, String key);
    /**
     * 删除表数据
     *
     * @param tableName 表名
     * @param column    条件列名(和表字段相同): 一个条件
     * @param key       字符串(逗号拼接1,2,3)
     * @return int
     */
    int deleteRows(String tableName, String column, Integer key);

    /**
     * 更新表格的deletef为1
     *
     * @param tableName  表名
     * @param ids 条件
     * @return int
     */
    int updateDeletef(String tableName, String ids);

    /**
     * 删除表数据
     *
     * @param tableName  表名
     * @param conditions 删除条件
     * @return
     */
    int deleteRows(String tableName, String conditions);

    /**
     * 获得select下拉列表
     *
     * @param tableName  表名
     * @param text       显示值->列名
     * @param value      值->列名
     * @param conditions 查询条件->为空：无条件查询
     * @return list
     */
    List<SelectBean> getSelectList(String tableName, String text, String value, String conditions);

    /**
     * 获得select下拉列表
     *
     * @param tableName  表名
     * @param text       显示值->列名
     * @param value      值->列名
     * @param conditions 查询条件->为空：无条件查询
     * @return json ->eg：[{0:aa},{1:bb}]
     */
    String getSelectJson(String tableName, String text, String value, String conditions);


}
