package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;
import java.util.Date;

public class SysMessage extends BaseBean {
    private Integer id;

    /**
     * 消息主题
     */
    private String title;

    /**
     * 文章作者：默认登录用户
     */
    private String author;

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

    private Date updateTime;

    private Date addTime;

    /**
     * 消息内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得消息主题
     * @return title 消息主题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置消息主题
     * @param title 消息主题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获得文章作者：默认登录用户
     * @return author 文章作者：默认登录用户
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置文章作者：默认登录用户
     * @param author 文章作者：默认登录用户
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 获得消息类型 0：系统消息 1:私信
     * @return type 消息类型 0：系统消息 1:私信
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置消息类型 0：系统消息 1:私信
     * @param type 消息类型 0：系统消息 1:私信
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获得是否置顶 0：不置顶  1：置顶
     * @return top 是否置顶 0：不置顶  1：置顶
     */
    public Byte getTop() {
        return top;
    }

    /**
     * 设置是否置顶 0：不置顶  1：置顶
     * @param top 是否置顶 0：不置顶  1：置顶
     */
    public void setTop(Byte top) {
        this.top = top;
    }

    /**
     * 获得重要 0：普通 1：重要
     * @return important 重要 0：普通 1：重要
     */
    public Byte getImportant() {
        return important;
    }

    /**
     * 设置重要 0：普通 1：重要
     * @param important 重要 0：普通 1：重要
     */
    public void setImportant(Byte important) {
        this.important = important;
    }

    /**
     * 获得紧急程度 0：不紧急 2：紧急
     * @return level 紧急程度 0：不紧急 2：紧急
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * 设置紧急程度 0：不紧急 2：紧急
     * @param level 紧急程度 0：不紧急 2：紧急
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获得消息内容
     * @return content 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}