package com.mx.system.model;

/**
 * 角色基础信息bean
 * @author mx
 *
 */
public class Role {
    private Integer id;

    private Integer addUserId;

    private Integer updateUserId;

    private String roleName;

    private Byte roleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Byte getRoleType() {
        return roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }
}