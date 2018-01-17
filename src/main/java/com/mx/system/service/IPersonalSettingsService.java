package com.mx.system.service;


import com.mx.common.util.response.ResponseFormat;
import com.mx.system.model.MasterUserEntity;

public interface IPersonalSettingsService {

    /**
     * 获得登录用户信息
     *
     * @param id 用户id
     * @return user
     */
    MasterUserEntity personalSettings(int id);

    /**
     * 编辑用户信息
     *
     * @param user 用户信息
     */
    void editUserInfo(MasterUserEntity user);

    /**
     * 密码重置
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return resp
     */
    ResponseFormat resetPassword(String oldPassword, String newPassword);


}
