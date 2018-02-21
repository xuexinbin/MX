package com.mx.system.controller;

import com.mx.common.controller.BaseWebController;
import com.mx.common.service.ICommonService;
import com.mx.generator.pojo.SysCode;
import com.mx.generator.pojo.SysDictionary;
import com.mx.system.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码管理controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/system/code")
public class CodeController extends BaseWebController {

    @Autowired
    ICodeService codeService;

    @Autowired
    ICommonService commonService;

    /**
     * 代码管理-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCodeView() {
        return "system/code";
    }

    /**
     * 获得已存代码
     *
     * @return model
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public ModelAndView getCode(Integer type) {
        List<SysCode> codeList = codeService.getCode(type);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("codeList", codeList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 添加代码
     *
     * @param code 代码信息
     * @return model
     */
    @RequestMapping(value = "/addCode", method = RequestMethod.POST)
    public ModelAndView addCode(SysCode code){
        Integer id = codeService.addCode(code);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 删除代码
     *
     * @param id 代码id
     * @return model
     */
    @RequestMapping(value = "/deleteCode", method = RequestMethod.POST)
    public ModelAndView deleteCode(String id) {
        commonService.deleteRows("sys_code", "id", id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

}
