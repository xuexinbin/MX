package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.controller.BaseWebController;
import com.mx.common.service.ICommonService;
import com.mx.generator.pojo.SysDepartment;
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
public class DepartmentController{

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
    public String getDepartmentGridData(SysDepartment sysDepartment) {
        // 获取部门列表grid数据
        List<SysDepartment> deptList = departmentService.getDepartmentGridData(sysDepartment);
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
}
