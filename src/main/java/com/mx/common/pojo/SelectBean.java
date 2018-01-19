package com.mx.common.pojo;

/**
 * 下拉菜单 结果共通bean
 *
 * @author mx
 */
public class SelectBean {

    public SelectBean() {
    }

    public SelectBean(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 显示文本
     */
    private String text;

    /**
     * 值
     */
    private String value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
