package com.mx.system.service;

import com.mx.system.model.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户管理service
 *
 * @author mx
 */
public interface IUserService {

    /**
     * 获得用户管理grid数据
     *
     * @param map 查询信息
     * @return list
     */
    List<User> getUserGridData(Map<String,String> map);

    /**
     * 更新用户
     *
     * @param file 用户头像
     * @param user 用户信息
     */
    void editUser(CommonsMultipartFile file, User user) throws IOException;

    /**
     * 获得用户详细信息
     *
     * @param id 用户id
     * @return user
     */
    User getUserInfoById(Integer id);
}
