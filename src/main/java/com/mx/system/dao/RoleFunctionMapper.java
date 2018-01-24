package com.mx.system.dao;

import com.mx.generator.pojo.SysFunction;
import com.mx.generator.pojo.SysRole;
import com.mx.generator.pojo.SysRoleFunction;
import com.mx.system.model.Function;
import com.mx.system.model.Role;
import com.mx.system.model.RoleFunction;
import com.mx.system.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限权限 Dao
 *
 * @author mx
 */
@Repository
public interface RoleFunctionMapper {

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
     * 添加角色
     *
     * @param role 角色信息
     */
    void addRole(SysRole role);

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
    void addRoleFunctions(List<SysRoleFunction> list);

    /**
     * 获得菜单列表
     *
     * @param type 0菜单 1权限 null全部
     * @return list
     */
    List<SysFunction> getFunctionList(Integer type);

}