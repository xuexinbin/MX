package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.controller.BaseWebController;
import com.mx.common.pojo.SelectBean;
import com.mx.common.service.ICommonService;
import com.mx.system.model.User;
import com.mx.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseWebController {

    @Autowired
    IUserService userService;

    @Autowired
    ICommonService commonService;

    /**
     * 用户管理-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUserView() {
        return "system/user";
    }

    /**
     * 新增/编辑用户dialog
     *
     * @return jsp
     */
    @RequestMapping(value = "editUserDialog", method = RequestMethod.GET)
    public String editUserDialog() {
        return "system/editUserDialog";
    }

    /**
     * 获得用户管理grid数据
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getUserGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getUserGridData(@RequestParam Map<String, String> map) {
        // 获取用户列表grid数据
        List<User> deptList = userService.getUserGridData(map);
        return JSON.toJSONString(deptList);
    }

    /**
     * 获得用户下拉列表
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getUserList() {
        String conditions = "enablef=0 AND deletef=0";
        List<SelectBean> list = commonService.getSelectList("sys_user", "true_name", "id", conditions);
        list.add(0, new SelectBean("无", ""));
        return JSON.toJSONString(list);
    }

    /**
     * 更新用户
     *
     * @param file 用户头像
     * @param user 用户信息
     * @return model
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@RequestParam(value = "file", required = false) CommonsMultipartFile file, User user) throws IOException {
        userService.editUser(file, user);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 删除用户
     *
     * @param id 用户ids
     * @return model
     */
    @RequestMapping(value = "/deleteUserByIds", method = RequestMethod.POST)
    public ModelAndView deleteDepartmentByIds(String id) {
        commonService.updateDeletef("sys_user", id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得用户详细信息
     *
     * @param id 用户id
     * @return model
     */
    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.POST)
    public ModelAndView getUserInfoById(Integer id) {
        User userInfo = userService.getUserInfoById(id);
        List<SelectBean> roleList = commonService.getSelectList("sys_role", "name", "id", null);
        roleList.add(0, new SelectBean("", "0"));
        String conditions = "enable=0";
        List<SelectBean> deptList = commonService.getSelectList("sys_department", "name", "id", conditions);
        deptList.add(0, new SelectBean("无", "0"));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", userInfo);
        resultMap.put("roleList", roleList);
        resultMap.put("deptList", deptList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 获得角色下拉列表
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getRoleList() {
        List<SelectBean> list = commonService.getSelectList("sys_role", "name", "id", null);
        list.add(0, new SelectBean("", "0"));
        return JSON.toJSONString(list);
    }


}
