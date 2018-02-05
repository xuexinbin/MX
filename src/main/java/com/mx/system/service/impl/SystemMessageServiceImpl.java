package com.mx.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.common.constant.CommonConstant;
import com.mx.common.pojo.PushMessageBean;
import com.mx.common.util.SessionManager;
import com.mx.common.util.websoket.MxWebSocketHandler;
import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import com.mx.generator.pojo.SysUserMessage;
import com.mx.system.dao.SystemMessageMapper;
import com.mx.system.model.SystemMessage;
import com.mx.system.service.ISystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统消息service
 *
 * @author mx
 */
@Service
@Transactional
public class SystemMessageServiceImpl implements ISystemMessageService {


    @Autowired
    SystemMessageMapper systemMessageMapper;

    @Override
    public JSONObject uploadImg(MultipartFile uploadFile) {
        JSONObject res = new JSONObject();
        try {
            // 上传文件名，转为小写
            String fileName = uploadFile.getOriginalFilename().toLowerCase();
            // 图片格式：jpg、png、jpeg、bmp
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")
                    && !fileName.endsWith(".jpeg") && !fileName.endsWith(".bmp")) {
                res.put("error", 1);
                res.put("message", "文件格式错误！只能上传图片文件！");
                return res;
            }

            // 文件最大1M
            long maxSize = 1024 * 1000;
            if (uploadFile.getSize() > maxSize) {
                res.put("error", 1);
                res.put("message", "上传图片失败！文件太大（超过1M）！");
                return res;
            }

            // 文件新名称：当前时间 + .png
            String newFileName = System.currentTimeMillis() + ".png";
            // 当前盘根目录 /uploadImg/message
            String baseUrl = SessionManager.getInstance().getSession().getServletContext().getRealPath("/");
            String localPath = baseUrl + CommonConstant.UPLOAD_MESSAGE_FOLDER;
            File localFile = new File(localPath, newFileName);
            if (!localFile.exists()) {
                if (!localFile.mkdirs()) {
                    res.put("error", 1);
                    res.put("message", "上传失败");
                    return res;
                }
            }
            // 上传图片
            uploadFile.transferTo(localFile);

            // 上传成功
            res.put("error", 0);
            res.put("url", CommonConstant.UPLOAD_MESSAGE_FOLDER + "/" + newFileName);
            return res;
        } catch (Exception e) {
            // 上传错误
            res.put("error", 1);
            res.put("message", "上传失败" + e.getMessage());
            return res;
        }
    }

    @Override
    public List<SystemMessage> getSystemMessageGridData(Map<String, String> map) {
        String type = map.get("type");
        // 0 系统消息 1 我的私信 2已发私信
        if ("2".equals(type)) {
            map.put("addUserId", SessionManager.getLoginUserId().toString());
            map.put("type", "1");
        } else {
            map.put("userId", SessionManager.getLoginUserId().toString());
        }

        return systemMessageMapper.getSystemMessageGridData(map);
    }

    @Override
    public void addMessage(SystemMessage message) throws Exception {
        message.setAuthor(SessionManager.getLoginUser().getTrueName());
        // 添加到消息列表
        systemMessageMapper.addMessage(message);
        String[] userIds = message.getUserIds().split(",");
        List<SysUserMessage> list = new ArrayList<>();
        Integer messageId = message.getId();
        for (String userId : userIds) {
            SysUserMessage um = new SysUserMessage();
            um.setMessageId(messageId);
            um.setUserId(Integer.valueOf(userId));
            list.add(um);
        }
        // 添加到未读列表
        systemMessageMapper.addUserMessage(list);

        // 推送消息给在线用户
        PushMessageBean pushMessage = new PushMessageBean();
//        pushMessage.setId(message.getId());
//        pushMessage.setType(message.getType());
//        pushMessage.setImportant(message.getImportant());
//        pushMessage.setLevel(message.getLevel());
//        pushMessage.setTop(message.getTop());
//        pushMessage.setTitle(message.getTitle());
        TextMessage textMessage = new TextMessage(JSON.toJSONString(pushMessage), true);
        MxWebSocketHandler.pushMessage(message.getUserIds(), textMessage);
    }

    @Override
    public SysMessage getMessage(Integer messageId) {
        return systemMessageMapper.getMessage(messageId);
    }

    @Override
    public List<SysUser> getMessageUser() {
        return systemMessageMapper.getMessageUser();
    }

}
