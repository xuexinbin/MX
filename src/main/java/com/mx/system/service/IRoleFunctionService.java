package com.mx.system.service;


import com.mx.system.model.Function;
import com.mx.system.model.Role;

import java.util.List;

public interface IRoleFunctionService {
    /**
     * 获得角色列表
     *
     * @return list
     */
    List<Role> getRoleList();

    /**
     * 编辑角色
     *
     * @param role 角色信息
     */
    void editRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void delRoleById(int id);

    /**
     * 获得菜单列表
     *
     * @param functionType 0菜单 1权限 null全部
     * @return list
     */
    List<Function> getFunctionList(Integer functionType);

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
