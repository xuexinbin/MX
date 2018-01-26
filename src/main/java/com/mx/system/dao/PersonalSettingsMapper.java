package com.mx.system.dao;

import com.mx.generator.pojo.SysUser;
import com.mx.system.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 个人设置Dao
 *
 * @author mx
 */
@Repository
public interface PersonalSettingsMapper {

    /**
     * 编辑个人基础信息
     *
     * @param user 个人信息
     */
    void editUserInfo(User user);

    /**
     * 密码重置
     *
     * @param map map
     */
    void editPassword(Map<String, Object> map);

    /**
     * 获得用户信息
     *
     * @param id 登录者id
     * @return user
     */
    SysUser getUserInfo(Integer id);

    /**
     * 获得用户信息
     *
     * @param map id；密码
     * @return user
     */
    SysUser getUserByPassword(Map<String, Object> map);
}