package com.mx.common.dao;

import com.mx.common.pojo.SelectBean;

import java.util.HashMap;
import java.util.List;

public interface ICommonDao {

    /**
     * 通过sql操作mysql
     *
     * @param sql
     * @return
     */
    int operateBySql(String sql);

    /**
     * 删除表数据
     * @param map 表名；列；条件
     * @return 删除数量
     */
    int deleteRows(HashMap<String, String> map);

    /**
     * 获得select下拉列表
     * @param map tableName 表名；key 显示text；value 值；conditions 查询条件
     * @return list
     */
    List<SelectBean> getSelectList(HashMap<String, Object> map);
}