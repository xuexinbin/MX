package com.mx.system.dao;


import com.mx.generator.pojo.SysFunction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单和功能管理 Dao
 *
 * @author mx
 */
@Repository
public interface FunctionMapper {

    /**
     * 获得菜单和功能列表grid数据
     *
     * @param map 查询信息
     * @return list
     */
    List<SysFunction> getFunctionGridData(Map<String, String> map);

    /**
     * 新增菜单和功能
     *
     * @param list 菜单和功能信息list
     */
    void addFunction(List<SysFunction> list);

    /**
     * 编辑菜单和功能
     *
     * @param dept 菜单和功能信息
     */
    void editFunction(SysFunction dept);

}