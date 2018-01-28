package com.mx.report.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.service.ICommonService;
import com.mx.generator.pojo.SysDictionary;
import com.mx.system.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/report/common")
public class ReportController {

    @Autowired
    ICommonService commonService;

    /**
     * 报表-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getDictionaryView() {
        return "report/one";
    }

}
