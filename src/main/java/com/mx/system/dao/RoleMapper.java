package com.mx.system.dao;

import com.mx.system.model.Role;

import java.util.List;

/**
 * 角色信息 Dao
 *
 * @author mx
 */
public interface RoleMapper {

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
     * 添加角色
     *
     * @param role 角色信息
     */
    void addRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void delRoleById(int id);

}