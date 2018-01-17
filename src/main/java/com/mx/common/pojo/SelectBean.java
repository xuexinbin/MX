package com.mx.common.pojo;

/**
 * 下拉菜单 结果共通bean
 *
 * @author mx
 */
public class SelectBean {
    /**
     * 显示文本
     */
    private String key;

    /**
     * 值
     */
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
