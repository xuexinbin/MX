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
     * @param userName 用户名
     * @param password 密码
     * @return user
     */
    SysUser logining(String userName, String password);

    /**
     * 获得显示菜单
     * @param type type:0菜单 1功能 null全部
     * @return json json
     */
    String getMenu(Integer type);
    /**
     * 获得权限
     * @param roleIds 角色ids
     * @return list
     */
    List<String> getFunctions(String roleIds);

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
