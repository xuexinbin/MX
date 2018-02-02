package com.mx.common.pojo;

import java.util.List;

/**
 * 菜单列表bean
 *
 * @author mx
 */
public class MenuBean {
    // id: ID需以字母开头
    private String id;
    // 父id
    private String parentId;
    // 名称
    private String name;
    // 跳转url
    private String url;
    // 图标
    private String icon;
    // navtab
    private String target;

    // 子菜单
    List<MenuBean> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<MenuBean> getChildren() {
        return children;
    }

    public void setChildren(List<MenuBean> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
