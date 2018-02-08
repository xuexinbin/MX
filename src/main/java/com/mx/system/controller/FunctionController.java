package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.controller.BaseWebController;
import com.mx.common.pojo.SelectBean;
import com.mx.common.service.ICommonService;
import com.mx.generator.pojo.SysFunction;
import com.mx.system.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单和功能管理controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/system/function")
public class FunctionController extends BaseWebController {

    @Autowired
    IFunctionService functionService;

    @Autowired
    ICommonService commonService;

    /**
     * 菜单和功能管理-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getDepartmentView() {
        return "system/function";
    }

    /**
     * 获得菜单或功能列表grid数据
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getFunctionGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getFunctionGridData(@RequestParam Map<String, String> map) {
        List<SysFunction> functionListList = functionService.getFunctionGridData(map);
        String conditions = "type=0 order by sort";
        List<SelectBean> parentList = commonService.getSelectList("sys_function", "name", "id", conditions);
        parentList.add(0, new SelectBean("无", "0"));
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("data", functionListList);
        resMap.put("parentList", parentList);
        return JSON.toJSONString(resMap);
    }

    /**
     * 编辑菜单和功能grid
     *
     * @param json json字符串
     * @return model
     */
    @RequestMapping(value = "/editFunction", method = RequestMethod.POST)
    public ModelAndView editFunction(String json) {
        functionService.editFunction(json);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 删除菜单或功能
     *
     * @param id 菜单或功能ids
     * @return model
     */
    @RequestMapping(value = "/deleteFunctionByIds", method = RequestMethod.POST)
    public ModelAndView deleteFunctionByIds(String id) {
        commonService.deleteRows("sys_function", "id", id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

}
