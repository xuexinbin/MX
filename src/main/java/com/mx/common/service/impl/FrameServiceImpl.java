package com.mx.common.service.impl;

import com.mx.generator.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.common.dao.IFrameDao;
import com.mx.common.service.IFrameService;

import java.util.HashMap;

@Service
@Transactional
public class FrameServiceImpl implements IFrameService {

    @Autowired
    private IFrameDao loginDao;


    @Override
    public SysUser logining(String userName, String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("password", password);

        SysUser sysUserRes = loginDao.logining(map);
        return sysUserRes;
    }
}
