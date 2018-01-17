package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.system.service.ISystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 系统消息controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/systemMessage")
public class SystemMessageController {

    @Autowired
    ISystemMessageService systemMessageService;


    /**
     * 系统消息-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "/systemMessage", method = RequestMethod.GET)
    public String getSystemMessageView() {
        return "system/systemMessage";
    }

    /**
     * 系统消息-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "/addNoticeDialog", method = RequestMethod.GET)
    public String getAddNoticeDialog() {
        return "system/addNoticeDialog";
    }

    /**
     * 上传系统消息图片
     *
     * @param uploadFile 文件
     * @return json对象
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JSONObject uploadImg(@RequestParam("imgFile") MultipartFile uploadFile) {
        return systemMessageService.uploadImg(uploadFile);
    }

}
