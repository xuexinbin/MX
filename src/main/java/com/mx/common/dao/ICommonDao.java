package com.mx.common.dao;

import com.mx.common.pojo.SelectBean;
import com.mx.generator.pojo.SysDictionary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ICommonDao {

    /**
     * 通过sql操作mysql
     *
     * @param sql sql
     * @return int
     */
    int operateBySql(String sql);

    /**
     * 删除表数据
     *
     * @param map 表名；列；条件
     * @return 删除数量
     */
    int deleteRows(HashMap<String, String> map);

    /**
     * 获得select下拉列表
     *
     * @param map tableName 表名；text 显示值；value 值；conditions 查询条件
     * @return list
     */
    List<SelectBean> getSelectList(HashMap<String, Object> map);

    /**
     * 更新表格的deletef为1
     *
     * @param map 表名；id；update_user_id
     * @return int
     */
    int updateDeletef(HashMap<String, Object> map);

    /**
     * 获得字典值列表
     *
     * @return
     */
    List<SysDictionary> getDictionaryListByType();

    /**
     * 更新指定列
     * UPDATE tableName SET column = columnValue, update_user_id = session中用户
     * WHERE id IN (${ids})
     * @param map map
     * @return int
     */
    int updateColumn(HashMap<String, Object> map);
}
