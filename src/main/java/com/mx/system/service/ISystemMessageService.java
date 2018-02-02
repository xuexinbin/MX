package com.mx.system.service;

import com.alibaba.fastjson.JSONObject;
import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import com.mx.system.model.SystemMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ISystemMessageService {

    /**
     * 上传系统消息图片
     *
     * @param uploadFile 图片
     * @return json对象
     */
    JSONObject uploadImg(MultipartFile uploadFile);

    /**
     * 获得系统消息列表
     *
     * @param map 参数
     * @return list
     */
    List<SystemMessage> getSystemMessageGridData(Map<String, String> map);

    /**
     * 添加消息
     *
     * @param message 消息
     */
    void addMessage(SystemMessage message) throws Exception;

    /**
     * 获得消息内容
     *
     * @param messageId id
     * @return message
     */
    SysMessage getMessage(Integer messageId);

    /**
     * 获得消息接收列表
     *
     * @return list
     */
    List<SysUser> getMessageUser();
}
