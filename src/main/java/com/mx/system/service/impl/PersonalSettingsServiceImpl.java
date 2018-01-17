package com.mx.system.service.impl;

import com.mx.common.constant.CommonConstant;
import com.mx.common.constant.ErrorCodeEnum;
import com.mx.common.util.SessionManager;
import com.mx.common.util.response.ResponseFormat;
import com.mx.system.dao.MasterUserEntityMapper;
import com.mx.system.dao.PersonalSettingsMapper;
import com.mx.system.model.MasterUserEntity;
import com.mx.system.service.IPersonalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人设置service
 *
 * @author mx
 */
@Service("personalSettingsService")
// TODO 事务 @Transactional
public class PersonalSettingsServiceImpl implements IPersonalSettingsService {

    @Autowired
    MasterUserEntityMapper masterUserEntityMapper;

    @Autowired
    PersonalSettingsMapper personalSettingsMapper;

    @Override
    public MasterUserEntity personalSettings(int id) {
        return masterUserEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editUserInfo(MasterUserEntity user) {
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        user.setId(userId);
        personalSettingsMapper.editUserInfo(user);
    }

    @Override
    public ResponseFormat resetPassword(String oldPassword, String newPassword) {
        ResponseFormat data = new ResponseFormat();
        Map<String, Object> map = new HashMap<>();
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        map.put("id", userId);
        map.put("password", oldPassword);
        map.put("newPassword", newPassword);
        // 判断原密码是否正确
        MasterUserEntity user = personalSettingsMapper.getUserInfo(map);
        if (user == null) {
            data.setErrorInfo(ErrorCodeEnum.PASSWORD_ERROR);
            return data;
        }
        // 重置新密码
        personalSettingsMapper.editPassword(map);
        return data;
    }


}
