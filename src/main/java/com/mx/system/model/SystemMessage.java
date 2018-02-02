package com.mx.system.model;

import com.mx.generator.pojo.SysMessage;

/**
 * 系统信息bean
 *
 * @author mx
 */
public class SystemMessage extends SysMessage {
    /**
     * 推送用户ids
     */
    private String userIds;
    /**
     * 是否已读，0未读，1已读
     */
    private Byte read;
    /**
     * 用户消息列表id
     */
    private Integer userMessageId;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Byte getRead() {
        return read;
    }

    public void setRead(Byte read) {
        this.read = read;
    }

    public Integer getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Integer userMessageId) {
        this.userMessageId = userMessageId;
    }
}