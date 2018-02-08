package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.common.controller.BaseWebController;
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
@RequestMapping("/system/dictionary")
public class DictionaryController  extends BaseWebController {

    @Autowired
    IDictionaryService dictionaryService;

    @Autowired
    ICommonService commonService;

    /**
     * 数据字典-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getDictionaryView() {
        return "system/dictionary";
    }

    /**
     * 获得字典类列表
     *
     * @return model
     */
    @RequestMapping(value = "/getDictionaryType", method = RequestMethod.POST)
    public ModelAndView getRoleList() {
        List<SysDictionary> dictionaryList = dictionaryService.getDictionaryType();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dictionaryList", dictionaryList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 获得字典值列表
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getDictionaryGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getDictionaryGridData(@RequestParam Map<String, String> map) {
        List<SysDictionary> dictionaryList = dictionaryService.getDictionaryGridData(map);
        return JSON.toJSONString(dictionaryList);
    }

    /**
     * 删除字典值
     *
     * @param id 字典id
     * @return model
     */
    @RequestMapping(value = "/deleteDictionaryByIds", method = RequestMethod.POST)
    public ModelAndView deleteDictionaryByIds(String id) {
        commonService.updateDeletef("sys_dictionary", id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 编辑字典值list
     *
     * @param json json字符串
     * @return model
     */
    @RequestMapping(value = "/editDictionary", method = RequestMethod.POST)
    public ModelAndView editDictionary(String json) {
        dictionaryService.editDictionary(json);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

}
