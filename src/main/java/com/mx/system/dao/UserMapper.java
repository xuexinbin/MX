package com.mx.system.dao;


import com.mx.system.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理 Dao
 *
 * @author mx
 */
@Repository
public interface UserMapper {

    /**
     * 获得用户管理grid数据
     *
     * @param map 查询信息
     * @return list
     */
    List<User> getUserGridData(Map<String,String> map);

    /**
     * 添加用户
     *
     * @param user 用户信息
     */
    void addUser(User user);

    /**
     * 编辑用户
     *
     * @param user 用户信息
     */
    void editUser(User user);

    /**
     * 编辑用户头像地址
     *
     * @param map 头像地址，用户id
     */
    void editUserAvatar(HashMap<String, Object> map);

    /**
     * 获得用户详细信息
     *
     * @param id 用户id
     * @return user
     */
    User getUserInfoById(Integer id);
}