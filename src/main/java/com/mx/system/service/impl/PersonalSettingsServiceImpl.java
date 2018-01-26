package com.mx.system.service.impl;

import com.mx.common.constant.CommonConstant;
import com.mx.common.constant.ErrorCodeEnum;
import com.mx.common.util.ImageUtil;
import com.mx.common.util.SessionManager;
import com.mx.common.util.response.ResponseFormat;
import com.mx.generator.pojo.SysUser;
import com.mx.system.dao.PersonalSettingsMapper;
import com.mx.system.model.User;
import com.mx.system.service.IPersonalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 个人设置service
 *
 * @author mx
 */
@Service
@Transactional
public class PersonalSettingsServiceImpl implements IPersonalSettingsService {


    @Autowired
    PersonalSettingsMapper personalSettingsMapper;

    @Override
    public SysUser getUserInfo() {
        return personalSettingsMapper.getUserInfo(SessionManager.getLoginUserId());
    }

    @Override
    public void editUserInfo(CommonsMultipartFile file, User user) throws IOException {
        Integer loginUserId = SessionManager.getLoginUserId();
        String fileName = loginUserId.toString() + "_" + new Date().getTime();
        String avatarUrl = ImageUtil.uploadImg(file, CommonConstant.UPLOAD_AVATAR_FOLDER, fileName);
        user.setAvatar(avatarUrl);
        user.setId(loginUserId);
        personalSettingsMapper.editUserInfo(user);
    }

    @Override
    public ResponseFormat resetPassword(String oldPassword, String newPassword) {
        ResponseFormat data = new ResponseFormat();
        Map<String, Object> map = new HashMap<>();
        map.put("id", SessionManager.getLoginUserId());
        map.put("password", oldPassword);
        map.put("newPassword", newPassword);
        // 判断原密码是否正确
        SysUser user = personalSettingsMapper.getUserByPassword(map);
        if (user == null) {
            data.setErrorInfo(ErrorCodeEnum.PASSWORD_ERROR);
            return data;
        } else {
            // 重置新密码
            personalSettingsMapper.editPassword(map);
            return data;
        }
    }

}
