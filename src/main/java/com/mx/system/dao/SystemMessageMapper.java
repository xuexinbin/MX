package com.mx.system.dao;

import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import com.mx.generator.pojo.SysUserMessage;
import com.mx.system.model.SystemMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统消息Dao
 *
 * @author mx
 */
@Repository
public interface SystemMessageMapper {

    /**
     * 获得系统消息列表
     *
     * @param map map
     * @return list
     */
    List<SystemMessage> getSystemMessageGridData(Map<String, String> map);

    /**
     * 添加消息
     *
     * @param message 消息
     */
    void addMessage(SystemMessage message);

    /**
     * 添加用户消息
     *
     * @param list list
     */
    void addUserMessage(List<SysUserMessage> list);

    /**
     * 获得消息内容
     *
     * @param messageId id
     * @return message
     */
    SysMessage getMessage(Integer messageId);

    /**
     * 获得消息接收列表
     *
     * @return list
     */
    List<SysUser> getMessageUser();
}