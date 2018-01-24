package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;

public class SysRole extends BaseBean {
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色类型0 系统默认 1自主添加
     */
    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得角色名
     * @return name 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获得角色类型0 系统默认 1自主添加
     * @return type 角色类型0 系统默认 1自主添加
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置角色类型0 系统默认 1自主添加
     * @param type 角色类型0 系统默认 1自主添加
     */
    public void setType(Byte type) {
        this.type = type;
    }
}