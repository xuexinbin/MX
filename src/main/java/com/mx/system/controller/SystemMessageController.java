package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.common.constant.CommonConstant;
import com.mx.common.controller.BaseWebController;
import com.mx.common.pojo.PushMessageBean;
import com.mx.common.service.ICommonService;
import com.mx.common.util.DictionaryUtil;
import com.mx.common.util.SessionManager;
import com.mx.common.util.websoket.MxWebSocketHandler;
import com.mx.generator.pojo.SysDictionary;
import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import com.mx.system.model.SystemMessage;
import com.mx.system.model.User;
import com.mx.system.service.ISystemMessageService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统消息controller
 *
 * @author mx
 */
@Controller
@RequestMapping("system/systemMessage")
public class SystemMessageController extends BaseWebController {

    @Autowired
    ISystemMessageService systemMessageService;

    @Autowired
    ICommonService commonService;

    /**
     * 系统消息-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getSystemMessageView(){
        return "system/systemMessage";
    }

    /**
     * 新消息-dialog
     *
     * @return jsp
     */
    @RequestMapping(value = "/addMessageDialog", method = RequestMethod.GET)
    public String addMessageDialog() {
        return "system/addMessageDialog";
    }

    /**
     * 新消息-dialog
     *
     * @return jsp
     */
    @RequestMapping(value = "/showMessage", method = RequestMethod.GET)
    public String showMessage(Integer messageId, ModelMap map) {
        SysMessage message = systemMessageService.getMessage(messageId);
        map.addAttribute("message", message);
        return "system/showMessage";
    }

    /**
     * 获得系统消息列表
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemMessageGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getSystemMessageGridData(@RequestParam Map<String, String> map) {
        List<SystemMessage> list = systemMessageService.getSystemMessageGridData(map);
        return JSON.toJSONString(list);
    }

    /**
     * 添加消息
     * @param message 消息
     * @return model
     */
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public ModelAndView addMessage(SystemMessage message) throws Exception {
        systemMessageService.addMessage(message);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
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

    /**
     * 删除消息
     *
     * @param userMessageId userMessageId
     * @return model
     */
    @RequestMapping(value = "/deleteMessageByIds", method = RequestMethod.POST)
    public ModelAndView deleteMessageByIds(String userMessageId) {
        commonService.updateDeletef("sys_user_message", userMessageId);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 消息已读
     *
     * @param id 消息id
     * @return model
     */
    @RequestMapping(value = "/readMessage", method = RequestMethod.POST)
    public ModelAndView readMessage(String id) {
        commonService.updateColumn("sys_user_message", "read", "1", "id",id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得消息接收列表
     *
     * @return json数组
     */
    @ResponseBody
    @RequestMapping(value = "/getMessageUser", method = RequestMethod.POST)
    public JSONArray getMessageUser() {
        ArrayList<JSONObject> list = new ArrayList<>();
        List<SysUser> userList = systemMessageService.getMessageUser();
        for (SysUser user : userList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("trueName", user.getTrueName());
            jsonObj.put("id", user.getId());
            list.add(jsonObj);
        }
        return JSONArray.fromObject(list);
    }

}
