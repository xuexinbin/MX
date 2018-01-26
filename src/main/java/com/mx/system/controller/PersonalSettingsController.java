package com.mx.system.controller;

import com.mx.common.util.response.ResponseFormat;
import com.mx.common.util.response.ResponseHandler;
import com.mx.generator.pojo.SysUser;
import com.mx.system.model.User;
import com.mx.system.service.IPersonalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 个人设置controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/system/personalSettings")
public class PersonalSettingsController {

    @Autowired
    IPersonalSettingsService personalSettingsService;

    /**
     * 个人设置-初始页面
     *
     * @param map modelMap
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String personalSettings(ModelMap map) {
        SysUser user = personalSettingsService.getUserInfo();
        map.addAttribute("user", user);
        return "system/personalSettings";
    }

    /**
     * 编辑个人基础信息
     *
     * @param file 个头像流
     * @param user 个人信息
     * @return model
     */
    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
    public ModelAndView editUserInfo(@RequestParam(value = "file", required = false) CommonsMultipartFile file, User user) throws IOException {
        personalSettingsService.editUserInfo(file, user);
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
}
