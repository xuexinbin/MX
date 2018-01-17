package com.mx.system.dao;

import com.mx.system.model.Function;
import com.mx.system.model.RoleFunction;
import com.mx.system.model.UserRole;

import java.util.List;

/**
 * 角色权限权限 Dao
 *
 * @author mx
 */
public interface RoleFunctionMapper {

    /**
     * 获得角色权限 str
     *
     * @param roleId 角色id
     * @return 角色权限
     */
    String getRoleFunctions(Integer roleId);

    /**
     * 添加角色权限list
     *
     * @param list 角色list
     */
    void addRoleFunctions(List<RoleFunction> list);

    /**
     * 删除角色的权限
     *
     * @param roleId 角色id
     */
    void deleteRoleFunctionsByRoleId(Integer roleId);

    /**
     * 获得菜单列表
     *
     * @param functionType 0菜单 1权限 null全部
     * @return list
     */
    List<Function> getFunctionList(Integer functionType);

    /**
     * 删除用户角色
     *
     * @param id 角色id
     */
    void deleteUserRole(Integer id);

    /**
     * 添加用户角色list
     *
     * @param userRoleList 角色ist
     */
    void addUserRoles(List<UserRole> userRoleList);
}