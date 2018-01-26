package com.mx.system.service;


import com.mx.common.util.response.ResponseFormat;
import com.mx.generator.pojo.SysUser;
import com.mx.system.model.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface IPersonalSettingsService {

    /**
     * 获得登录用户信息
     *
     * @return user
     */
    SysUser getUserInfo();

    /**
     * 编辑用户信息
     *
     * @param file 头像流
     * @param user 用户信息
     */
    void editUserInfo(CommonsMultipartFile file, User user) throws IOException;

    /**
     * 密码重置
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return resp
     */
    ResponseFormat resetPassword(String oldPassword, String newPassword);


}
