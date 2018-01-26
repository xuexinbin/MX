package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;

public class SysDictionary extends BaseBean {
    private Integer id;

    /**
     * 字典分类名称
     */
    private String type;

    /**
     * 字典项编码
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否禁用：0正常，1禁用
     */
    private Byte enablef;

    /**
     * 是否删除：0正常，1删除
     */
    private Byte deletef;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得字典分类名称
     * @return type 字典分类名称
     */
    public String getType() {
        return type;
    }

    /**
     * 设置字典分类名称
     * @param type 字典分类名称
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获得字典项编码
     * @return name 字典项编码
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典项编码
     * @param name 字典项编码
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获得字典值
     * @return value 字典值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置字典值
     * @param value 字典值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获得排序
     * @return sort 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获得是否禁用：0正常，1禁用
     * @return enablef 是否禁用：0正常，1禁用
     */
    public Byte getEnablef() {
        return enablef;
    }

    /**
     * 设置是否禁用：0正常，1禁用
     * @param enablef 是否禁用：0正常，1禁用
     */
    public void setEnablef(Byte enablef) {
        this.enablef = enablef;
    }

    /**
     * 获得是否删除：0正常，1删除
     * @return deletef 是否删除：0正常，1删除
     */
    public Byte getDeletef() {
        return deletef;
    }

    /**
     * 设置是否删除：0正常，1删除
     * @param deletef 是否删除：0正常，1删除
     */
    public void setDeletef(Byte deletef) {
        this.deletef = deletef;
    }
}