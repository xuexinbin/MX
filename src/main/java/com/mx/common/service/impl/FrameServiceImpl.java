package com.mx.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.mx.common.pojo.MenuBean;
import com.mx.common.util.SessionManager;
import com.mx.generator.pojo.SysFunction;
import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.common.dao.IFrameDao;
import com.mx.common.service.IFrameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class FrameServiceImpl implements IFrameService {

    @Autowired
    private IFrameDao frameDao;

    @Override
    public SysUser logining(String userName, String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("password", password);

        return frameDao.logining(map);
    }


    @Override
    public String getFunctionList(Integer type) {
        String roleIds = SessionManager.getLoginUser().getRoleIds();
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("roleIds", roleIds);
        List<MenuBean> menuList = frameDao.getFunctionList(map);

        // 返回list
        List<MenuBean> newList = new ArrayList<>();
        HashMap<String, MenuBean> menuMap = new HashMap<>();
        // 封装map：同一个对象，list map两个指向
        for (MenuBean menu : menuList) {
            menuMap.put(menu.getId(), menu);
        }

        for (MenuBean menu : menuList) {
            MenuBean m = menuMap.get(menu.getParentId());
            if (m != null) {
                List<MenuBean> children = m.getChildren();
                if (children == null) {
                    ArrayList<MenuBean> newChildren = new ArrayList<>();
                    newChildren.add(menu);
                    m.setChildren(newChildren);
                } else {
                    children.add(menu);
                }
            } else {
                newList.add(menu);
            }
        }

        return JSON.toJSONString(newList);
    }

    @Override
    public List<SysMessage> getUnreadMessage() {
        return frameDao.getUnreadMessage(SessionManager.getLoginUserId());
    }

    @Override
    public Integer getUnreadMessageCount() {
        return frameDao.getUnreadMessageCount(SessionManager.getLoginUserId());
    }
}
