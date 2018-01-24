package com.mx.system.service.impl;

import com.mx.common.constant.CommonConstant;
import com.mx.common.util.ImageUtil;
import com.mx.system.dao.UserMapper;
import com.mx.system.model.User;
import com.mx.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理service
 *
 * @author mx
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserGridData(Map<String,String> map) {
        return userMapper.getUserGridData(map);
    }

    @Override
    public void editUser(CommonsMultipartFile file, User user) throws IOException {
        if (user.getId() == null) {
            userMapper.addUser(user);
            String fileName = user.getId().toString() + "_" + new Date().getTime();
            String avatarUrl = ImageUtil.uploadImg(file, CommonConstant.UPLOAD_AVATAR_FOLDER, fileName);
            if (avatarUrl != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("avatar", avatarUrl);
                map.put("id", user.getId());
                userMapper.editUserAvatar(map);
            }
        } else {
            String fileName = user.getId().toString() + "_" + new Date().getTime();
            String avatarUrl = ImageUtil.uploadImg(file, CommonConstant.UPLOAD_AVATAR_FOLDER, fileName);
            user.setAvatar(avatarUrl);
            userMapper.editUser(user);
        }
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userMapper.getUserInfoById(id);
    }

}
