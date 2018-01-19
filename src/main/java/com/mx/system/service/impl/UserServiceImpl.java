package com.mx.system.service.impl;

import com.mx.common.constant.CommonConstant;
import com.mx.common.util.GridUtil;
import com.mx.common.util.SessionManager;
import com.mx.generator.pojo.SysDepartment;
import com.mx.system.dao.DepartmentMapper;
import com.mx.system.dao.UserMapper;
import com.mx.system.model.User;
import com.mx.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 部门管理service
 *
 * @author mx
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void editDepartment(String json) {
        List<SysDepartment> insertList = GridUtil.getGridInsertList(json, SysDepartment.class);
        List<SysDepartment> updataList = GridUtil.getGridUpdateList(json, SysDepartment.class);
        // 逐条update
        for (SysDepartment sd : updataList) {
            userMapper.editDepartment(sd);
        }
        // 批量添加新增数据
        if (insertList.size() > 0) {
            userMapper.addDepartment(insertList);
        }
    }

    @Override
    public void deleteDepartmentByIds(String ids) {
        userMapper.deleteDepartmentByIds(ids);
    }

    @Override
    public List<User> getUserGridData(User user) {
        return userMapper.getUserGridData(user);
    }

    @Override
    public void editUser(CommonsMultipartFile file, User user) throws IOException {
        if (user.getId() == null) {
            userMapper.addUser(user);
            String avatarUrl = uploadImg(file, user.getId());
            user.setAvatar(avatarUrl);
        }
        userMapper.editUser(user);
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userMapper.getUserInfoById(id);
    }

    /**
     * 上传图片
     *
     * @param file 头像
     * @param id   用户id
     * @return url
     */
    private String uploadImg(CommonsMultipartFile file, Integer id) throws IOException {
        String url = null;
        if (file != null) {
            // 文件新名称：当前时间 + .png
            String newFileName = id + ".png";
            // 项目目录 /uploadImg/avatar
            String aa = SessionManager.getInstance().getSession().getServletContext().getRealPath("/");
            String localPath = aa + CommonConstant.UPLOAD_AVATAR_FOLDER;
            File localFile = new File(localPath, newFileName);
            if (!localFile.exists()) {
                if (!localFile.mkdirs()) {
                    return "";
                }
            }
            // 上传图片：出错往外抛，自动回滚
            file.transferTo(localFile);
            url = CommonConstant.UPLOAD_AVATAR_FOLDER + "/" + newFileName;
        }
        return url;
    }

}
