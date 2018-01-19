package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;

public class SysRole extends BaseBean {
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色类型0 系统默认 1自主添加
     */
    private Byte roleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得角色名
     * @return role_name 角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名
     * @param roleName 角色名
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获得角色类型0 系统默认 1自主添加
     * @return role_type 角色类型0 系统默认 1自主添加
     */
    public Byte getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型0 系统默认 1自主添加
     * @param roleType 角色类型0 系统默认 1自主添加
     */
    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }
}