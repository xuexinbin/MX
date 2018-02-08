package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.controller.BaseWebController;
import com.mx.common.pojo.SelectBean;
import com.mx.common.service.ICommonService;
import com.mx.system.model.Department;
import com.mx.system.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/system/department")
public class DepartmentController extends BaseWebController {

    @Autowired
    IDepartmentService departmentService;

    @Autowired
    ICommonService commonService;

    /**
     * 部门管理-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getDepartmentView() {
        return "system/department";
    }

    /**
     * 获得部门列表grid数据
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getDepartmentGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getDepartmentGridData(Department department) {
        // 获取部门列表grid数据
        List<Department> deptList = departmentService.getDepartmentGridData(department);
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
        departmentService.editDepartment(json);
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
     * 获得部门下拉列表
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/getSearhDepartmentList", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getSearhDepartmentList() {
        String conditions = "enable=0";
        List<SelectBean> list = commonService.getSelectList("sys_department", "name", "id", conditions);
        list.add(0, new SelectBean("全部", ""));
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


}
