package com.mx.common.pojo;

/**
 * 消息推送bean
 *
 * @author mx
 */
public class PushMessageBean {
    private Integer id;

    /**
     * 消息主题
     */
    private String title;

    /**
     * 消息类型 0：系统消息 1:私信
     */
    private Byte type;

    /**
     * 是否置顶 0：不置顶  1：置顶
     */
    private Byte top;

    /**
     * 重要 0：普通 1：重要
     */
    private Byte important;

    /**
     * 紧急程度 0：不紧急 2：紧急
     */
    private Byte level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }

    public Byte getImportant() {
        return important;
    }

    public void setImportant(Byte important) {
        this.important = important;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}
