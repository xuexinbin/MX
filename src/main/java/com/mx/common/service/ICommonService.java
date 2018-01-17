package com.mx.common.service;

import com.mx.common.pojo.SelectBean;
import com.mx.common.pojo.TableParam;

import java.util.List;

public interface ICommonService {

    /**
     * 更新多行数据
     *
     * @param tableId
     * @param conditionCol
     * @param updateData
     * @return
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
     * @param column    条件列名(和表字段相同)
     * @param key       字符串(逗号拼接1,2,3)
     * @return
     */
    int deleteRows(String tableName, String column, String key);

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
     * @param tableName 表名
     * @param key 显示text->列名
     * @param value 值->列名
     * @param conditions 查询条件->为空：无条件查询
     * @return
     */
    List<SelectBean> getSelectList(String tableName, String key, String value, String conditions);


}
