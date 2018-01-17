package com.mx.system.dao;

import com.mx.system.model.MasterUserEntity;

import java.util.Map;

/**
 * 个人设置Dao
 *
 * @author mx
 */
public interface PersonalSettingsMapper {

    /**
     * 编辑个人基础信息
     *
     * @param user 个人信息
     */
    void editUserInfo(MasterUserEntity user);

    /**
     * 密码重置
     *
     * @param map map
     */
    void editPassword(Map<String, Object> map);

    /**
     * 获得用户信息
     *
     * @param map map
     * @return user
     */
    MasterUserEntity getUserInfo(Map<String, Object> map);


}