package com.mx.system.service;

import com.mx.generator.pojo.SysFunction;

import java.util.List;
import java.util.Map;

/**
 * 菜单和功能管理service
 *
 * @author mx
 */
public interface IFunctionService {

    /**
     * 编辑菜单和功能rid
     *
     * @param json 部门信息
     */
    void editFunction(String json);

    /**
     * 获得菜单或功能列表grid数据
     *
     * @param map 查询条件
     * @return list
     */
    List<SysFunction> getFunctionGridData(Map<String, String> map);
}
