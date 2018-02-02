package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;

public class SysFunction extends BaseBean {
    private Integer id;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 菜单／权限名称
     */
    private String name;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型：0菜单 1权限
     */
    private Byte type;

    /**
     * 排序
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得父菜单id
     * @return parent_id 父菜单id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单id
     * @param parentId 父菜单id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获得菜单／权限名称
     * @return name 菜单／权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单／权限名称
     * @param name 菜单／权限名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获得菜单编码
     * @return code 菜单编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置菜单编码
     * @param code 菜单编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获得菜单url
     * @return url 菜单url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单url
     * @param url 菜单url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获得图标
     * @return icon 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获得类型：0菜单 1权限
     * @return type 类型：0菜单 1权限
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型：0菜单 1权限
     * @param type 类型：0菜单 1权限
     */
    public void setType(Byte type) {
        this.type = type;
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
}