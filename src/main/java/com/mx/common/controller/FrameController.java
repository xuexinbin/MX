package com.mx.common.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.constant.CommonConstant;
import com.mx.common.constant.ErrorCodeEnum;
import com.mx.common.service.IFrameService;
import com.mx.common.util.SessionManager;
import com.mx.common.util.response.ResponseFormat;
import com.mx.common.util.response.ResponseHandler;
import com.mx.generator.pojo.SysMessage;
import com.mx.generator.pojo.SysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 框架 controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/")
public class FrameController {
    private static Logger logger = LogManager.getLogger(FrameController.class);

    @Autowired
    private IFrameService frameService;

    /**
     * 登录页面
     *
     * @return jsp
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginIndex() {
        return "common/login";
    }

    /**
     * 管理页面
     *
     * @return jsp
     */
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String adminIndex(ModelMap map) {
        Integer messageCount = frameService.getUnreadMessageCount();
        map.addAttribute("messageCount", messageCount);
        map.addAttribute("user", SessionManager.getLoginUser());
        return "common/mx";
    }

    /**
     * 空白页面
     *
     * @return jsp
     */
    @RequestMapping(value = "blank", method = RequestMethod.GET)
    public String blankView() {
        return "common/blank";
    }


    /**
     * 登录验证
     *
     * @param userName 用户名
     * @param password 密码->md5
     * @param response res
     */
    @RequestMapping(value = "frame/logining", method = RequestMethod.POST)
    public void logining(String userName, String password, HttpServletResponse response) {
        SysUser sysUserInfo = frameService.logining(userName, password);
        ResponseFormat rf = new ResponseFormat();
        if (sysUserInfo == null) {
            rf.setErrorInfo(ErrorCodeEnum.USER_NOT_EXSIT);
            logger.info("登录失败：" + userName);
        } else {
            SessionManager.getInstance().setValue(CommonConstant.SESSION_USER_ID, sysUserInfo.getId());
            SessionManager.getInstance().setValue(CommonConstant.SESSION_USER, sysUserInfo);
            List<String> functions = frameService.getFunctions(sysUserInfo.getRoleIds());
            SessionManager.getInstance().setValue(CommonConstant.SESSION_FUNCTION, functions);
            HashMap<String, Object> map = new HashMap<>();
            map.put("user", JSON.toJSONString(sysUserInfo));
            map.put("functions", JSON.toJSONString(functions));
            rf.setData(map);
            logger.info("登录成功：" + userName);
        }
        ResponseHandler.write(response, rf);
    }

    /**
     * 退出登录
     *
     * @return model
     */
    @RequestMapping(value = "frame/loginOut", method = RequestMethod.POST)
    public ModelAndView loginOut() {
        logger.info("login out!");
        // 注销删除session信息
        SessionManager.getInstance().clearSession();
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得显示菜单
     *
     * @return json菜单
     */
    @ResponseBody
    @RequestMapping(value = "frame/getMenu", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getMenu() {
        // 获得显示菜单
        return frameService.getMenu(0);
    }

    /**
     * 获得未读消息 limit 10
     *
     * @return mode
     */
    @RequestMapping(value = "frame/getUnreadMessage", method = RequestMethod.POST)
    public ModelAndView getUnreadMessage() {
        List<SysMessage> messageList = frameService.getUnreadMessage();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("messageList", messageList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

}
