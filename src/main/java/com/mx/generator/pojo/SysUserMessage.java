package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;
import java.util.Date;

public class SysUserMessage extends BaseBean {
    private Integer id;

    /**
     * 消息id
     */
    private Integer messageId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 是否已读，0未读，1已读
     */
    private Byte read;

    /**
     * 是否删除，0正常，1删除
     */
    private Byte deletef;

    private Date updateTime;

    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得消息id
     * @return message_id 消息id
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * 设置消息id
     * @param messageId 消息id
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * 获得用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获得是否已读，0未读，1已读
     * @return read 是否已读，0未读，1已读
     */
    public Byte getRead() {
        return read;
    }

    /**
     * 设置是否已读，0未读，1已读
     * @param read 是否已读，0未读，1已读
     */
    public void setRead(Byte read) {
        this.read = read;
    }

    /**
     * 获得是否删除，0正常，1删除
     * @return deletef 是否删除，0正常，1删除
     */
    public Byte getDeletef() {
        return deletef;
    }

    /**
     * 设置是否删除，0正常，1删除
     * @param deletef 是否删除，0正常，1删除
     */
    public void setDeletef(Byte deletef) {
        this.deletef = deletef;
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
}