package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.common.pojo.SelectBean;
import com.mx.common.service.ICommonService;
import com.mx.system.model.Department;
import com.mx.system.model.User;
import com.mx.system.service.IDepartmentService;
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
public class UserController {

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
    public String getUserGridData(User user) {
        // 获取用户列表grid数据
        List<User> deptList = userService.getUserGridData(user);
        return JSON.toJSONString(deptList);
    }

    /**
     * 编辑部门grid
     *
     * @param json json字符串
     * @return model
     */
    @RequestMapping(value = "/editDepartment", method = RequestMethod.POST)
    public ModelAndView editDepartment(String json) {
        userService.editDepartment(json);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 删除部门
     *
     * @param id 部门ids
     * @return model
     */
    @RequestMapping(value = "/deleteDepartmentByIds", method = RequestMethod.POST)
    public ModelAndView deleteDepartmentByIds(String id) {
        commonService.deleteRows("sys_department", "id", id);
        // departmentService.deleteDepartmentByIds(id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得部门下拉列表
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getDepartmentList() {
        String conditions = "enable=0";
        List<SelectBean> list = commonService.getSelectList("sys_department", "name", "id", conditions);
        list.add(0, new SelectBean("无", "0"));
        return JSON.toJSONString(list);
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
     * @param file 用户头像
     * @param user 用户信息
     * @return model
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@RequestParam(value = "file", required = false) CommonsMultipartFile file) throws IOException {
        //userService.editUser(file, user);
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
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", userInfo);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }


}
