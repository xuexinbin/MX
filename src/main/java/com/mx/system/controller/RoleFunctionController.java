package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.service.ICommonService;
import com.mx.generator.pojo.SysFunction;
import com.mx.generator.pojo.SysRole;
import com.mx.system.service.IRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色权限controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/system/roleFunction")
public class RoleFunctionController {

    @Autowired
    IRoleFunctionService roleFunctionService;

    @Autowired
    ICommonService commonService;

    /**
     * 角色及权限管理-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getRoleFunctionView() {
        return "system/roleFunction";
    }

    /**
     * 编辑角色dialog
     *
     * @return jsp
     */
    @RequestMapping(value = "/editRoleDialog", method = RequestMethod.GET)
    public String getEditRoleDialog() {
        return "system/editRoleDialog";
    }

    /**
     * 获得角色列表
     *
     * @return model
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public ModelAndView getRoleList() {
        List<SysRole> roleList = roleFunctionService.getRoleList();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleList", roleList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 编辑角色：编辑:id不为空；新增:id为空
     *
     * @param role 角色
     * @return model
     */
    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public ModelAndView editRole(SysRole role) {
        roleFunctionService.editRole(role);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return model
     */
    @RequestMapping(value = "/delRoleById", method = RequestMethod.POST)
    public ModelAndView delDepartmentById(String id) {
        commonService.deleteRows("sys_role", "id", id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得菜单列表
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getFunctionList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getFunctionList(Integer type) {
        // 获得菜单列表
        List<SysFunction> functionList = roleFunctionService.getFunctionList(type);
        return JSON.toJSONString(functionList);
    }

    /**
     * 获得角色权限str
     *
     * @return model
     */
    @RequestMapping(value = "/getRoleFunctions", method = RequestMethod.POST)
    public ModelAndView getRoleFunctionList(Integer roleId) {
        String roleFunctions = roleFunctionService.getRoleFunctions(roleId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleFunctions", roleFunctions);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 编辑角色权限
     *
     * @return model
     */
    @RequestMapping(value = "/editRoleFunctions", method = RequestMethod.POST)
    public ModelAndView editRoleFunctions(Integer roleId, String functionIds) {
        roleFunctionService.editRoleFunctions(roleId, functionIds);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

}
