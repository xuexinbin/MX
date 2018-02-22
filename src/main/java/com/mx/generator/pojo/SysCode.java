package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;

public class SysCode extends BaseBean {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 0:表单
     */
    private Byte type;

    /**
     * 代码
     */
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获得0:表单
     * @return type 0:表单
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置0:表单
     * @param type 0:表单
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获得代码
     * @return code 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}