package com.mx.common.service;

import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;

import java.util.List;

/**
 * 框架共通service
 */
public interface IFrameService {

    /**
     * 登录认证
     *
     * @param userName
     * @param password
     * @return
     */
    SysUser logining(String userName, String password);

    /**
     * 获得显示菜单
     * @param type type:0菜单 1功能 null全部;roleIds
     * @return json json
     */
    String getFunctionList(Integer type);

    /**
     * 获得未读消息最多5个
     * @return list
     */
    List<SysMessage> getUnreadMessage();

    /**
     * 获得未读消息数量
     * @return count
     */
    Integer getUnreadMessageCount();
}
