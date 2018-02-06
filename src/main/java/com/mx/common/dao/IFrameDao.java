package com.mx.common.dao;


import com.mx.common.pojo.MenuBean;
import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface IFrameDao {
    /**
     * 登录验证
     *
     * @param map username,password
     * @return user
     */
    SysUser logining(HashMap<String, Object> map);

    /**
     * 获得显示菜单
     *
     * @param map type:0菜单 1功能 null全部;roleIds
     * @return list
     */
    List<MenuBean> getFunctionList(HashMap<String, Object> map);

    /**
     * 获得未读消息
     * @param loginUserId id
     * @return list
     */
    List<SysMessage> getUnreadMessage(Integer loginUserId);

    /**
     * 获得未读消息数量
     * @param loginUserId id
     * @return count
     */
    Integer getUnreadMessageCount(Integer loginUserId);

    /**
     * 获得权限
     * @return list
     * @param roleIds
     */
    List<String> getFunctions(String roleIds);

    /**
     * 获得角色名称
     * @param roleIds 角色ids
     * @return names
     */
    String getRoleName(String roleIds);
}
