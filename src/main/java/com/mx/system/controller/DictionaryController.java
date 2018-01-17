package com.mx.system.controller;

import com.alibaba.fastjson.JSON;
import com.mx.system.model.Dictionary;
import com.mx.system.model.MasterDictionaryEntity;
import com.mx.system.service.IDictionaryService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    IDictionaryService dictionaryService;

    /**
     * 数据字典-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "/dictionary", method = RequestMethod.GET)
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
        List<MasterDictionaryEntity> dictionaryList = dictionaryService.getDictionaryType();
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
    @RequestMapping(value = "/getDictionarysByCode", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getDictionarysByCode(String dictionaryCode) {

        List<MasterDictionaryEntity> dictionaryList = dictionaryService.getDictionarysByCode(null, dictionaryCode);
        return JSON.toJSONString(dictionaryList);
    }

    /**
     * 删除字典值
     *
     * @param id 字典id
     * @return model
     */
    @RequestMapping(value = "/deleteDictionaryById", method = RequestMethod.POST)
    public ModelAndView deleteDictionaryById(String id) {
        dictionaryService.deleteDictionaryById(id);
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
        JSONArray ja = JSONArray.fromObject(json);
        List<Dictionary> list = (ArrayList) JSONArray.toCollection(ja, Dictionary.class);

        dictionaryService.editDictionary(list);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

}
