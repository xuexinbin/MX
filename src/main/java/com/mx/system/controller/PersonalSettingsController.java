package com.mx.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.mx.common.constant.CommonConstant;
import com.mx.common.util.SessionManager;
import com.mx.common.util.response.ResponseFormat;
import com.mx.common.util.response.ResponseHandler;
import com.mx.system.model.MasterUserEntity;
import com.mx.system.service.IPersonalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 个人设置controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/personalSettings")
public class PersonalSettingsController {

    @Autowired
    IPersonalSettingsService personalSettingsService;

    /**
     * 个人设置-初始页面
     *
     * @param map modelMap
     * @return jsp
     */
    @RequestMapping(value = "/personalSettings", method = RequestMethod.GET)
    public String personalSettings(ModelMap map) {
        // Session 获得登录者id
        Integer userId = (int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        MasterUserEntity user = personalSettingsService.personalSettings(userId);
        map.addAttribute("user", user);
        return "system/personalSettings";
    }

    /**
     * 编辑个人基础信息
     *
     * @param user 个人信息
     * @return model
     */
    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
    public ModelAndView editUserInfo(MasterUserEntity user) {
        personalSettingsService.editUserInfo(user);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 密码重置
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param response    resp
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public void resetPassword(String oldPassword, String newPassword, HttpServletResponse response) {
        ResponseFormat data = personalSettingsService.resetPassword(oldPassword, newPassword);
        ResponseHandler.write(response, data);
    }

    /**
     * 上传头像
     *
     * @param uploadFile 头像
     * @return json对象
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JSONObject uploadImg(@RequestParam("file") MultipartFile uploadFile) {
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
//        return departmentUserService.uploadImg(userId.toString(), uploadFile);
        return null;
    }

}
