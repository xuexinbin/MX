package com.mx.system.service;


import com.mx.generator.pojo.SysFunction;
import com.mx.generator.pojo.SysRole;

import java.util.List;

public interface IRoleFunctionService {
    /**
     * 获得角色列表
     *
     * @return list
     */
    List<SysRole> getRoleList();

    /**
     * 编辑角色
     *
     * @param role 角色信息
     */
    void editRole(SysRole role);

    /**
     * 获得菜单列表
     *
     * @param type 0菜单 1权限 null全部
     * @return list
     */
    List<SysFunction> getFunctionList(Integer type);

    /**
     * 获得角色权限 str
     *
     * @param roleId 角色id
     * @return 角色权限
     */
    String getRoleFunctions(Integer roleId);

    /**
     * 编辑角色权限
     *
     * @param roleId      角色id
     * @param functionIds 角色权限
     */
    void editRoleFunctions(Integer roleId, String functionIds);
}
