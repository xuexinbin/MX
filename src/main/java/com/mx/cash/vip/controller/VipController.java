package com.mx.cash.vip.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.cash.vip.model.Vip;
import com.mx.cash.vip.model.VipRecharge;
import com.mx.cash.vip.service.IVipService;
import com.mx.common.constant.CommonConstant;
import com.mx.common.controller.BaseWebController;
import com.mx.common.service.ICommonService;
import com.mx.common.util.DictionaryUtil;
import com.mx.generator.pojo.CashVip;
import com.mx.generator.pojo.CashVipRecharge;
import net.sf.json.JSONArray;
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
 * 会员管理controller
 *
 * @author mx
 */
@Controller
@RequestMapping("/cash/vip")
public class VipController extends BaseWebController {

    @Autowired
    IVipService vipService;

    @Autowired
    ICommonService commonService;

    /**
     * 会员信息-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "vipInfo", method = RequestMethod.GET)
    public String getVipInfoView() {
        return "cash/vip/vipInfo";
    }

    /**
     * 新增/编辑会员dialog
     *
     * @return jsp
     */
    @RequestMapping(value = "editVipDialog", method = RequestMethod.GET)
    public String editVipDialog() {
        return "cash/vip/editVipInfoDialog";
    }

    /**
     * 获得会员管理grid数据
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getVipInfoGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getVipInfoGridData(@RequestParam Map<String, String> map) {
        // 获取用户列表grid数据
        List<Vip> vipList = vipService.getVipInfoGridData(map);
        return JSON.toJSONString(vipList);
    }

    /**
     * 更新会员
     *
     * @param vip 会员信息
     * @return model
     */
    @RequestMapping(value = "/editVip", method = RequestMethod.POST)
    public ModelAndView editVip(Vip vip) {
        vipService.editVip(vip);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 删除会员
     *
     * @param id 会员ids
     * @return model
     */
    @RequestMapping(value = "/deleteVipByIds", method = RequestMethod.POST)
    public ModelAndView deleteVipByIds(String id) {
        commonService.updateDeletef("cash_vip", id);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得会员详细信息
     *
     * @param id 用户id
     * @return model
     */
    @RequestMapping(value = "/getVipInfoById", method = RequestMethod.POST)
    public ModelAndView getVipInfoById(Integer id) {
        Vip vipInfo = vipService.getVipInfoById(id);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vipInfo", vipInfo);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    /**
     * 会员充值-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "recharge", method = RequestMethod.GET)
    public String getRechargeView() {
        return "cash/vip/recharge";
    }

    /**
     * 新增充值dialog
     *
     * @return jsp
     */
    @RequestMapping(value = "addRechargeDialog", method = RequestMethod.GET)
    public String addRechargeDialog() {
        return "cash/vip/addRechargeDialog";
    }

    /**
     * 获得充值会员findGrid
     *
     * @return json数组
     */
    @ResponseBody
    @RequestMapping(value = "/getVipFindGrid", method = RequestMethod.POST)
    public JSONArray getVipFindGrid() {
        ArrayList<JSONObject> list = new ArrayList<>();
        List<Vip> vipList = vipService.getVipFindGrid();
        for (Vip vip : vipList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id",vip.getId());
            jsonObj.put("name",vip.getName());
            jsonObj.put("number",vip.getNumber());
            jsonObj.put("type",vip.getType());
            jsonObj.put("phone",vip.getPhone());
            jsonObj.put("remain",vip.getRemain());
            list.add(jsonObj);
        }
        return JSONArray.fromObject(list);
    }

    /**
     * 添加充值信息
     *
     * @param vipRecharge 充值信息
     * @return model
     */
    @RequestMapping(value = "/addRecharge", method = RequestMethod.POST)
    public ModelAndView addRecharge(VipRecharge vipRecharge) {
        vipService.addRecharge(vipRecharge);
        return new ModelAndView(new MappingJackson2JsonView(), new HashMap<>());
    }

    /**
     * 获得充值grid数据
     *
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getVipRechargeGridData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getVipRechargeGridData(@RequestParam Map<String, String> map) {
        // 获取充值列表grid数据
        List<VipRecharge> rechargeList = vipService.getVipRechargeGridData(map);
        return JSON.toJSONString(rechargeList);
    }

    /**
     * 获得充值详细信息
     *
     * @param id 用户id
     * @return model
     */
    @RequestMapping(value = "/getVipRechargeInfoById", method = RequestMethod.POST)
    public ModelAndView getVipRechargeInfoById(Integer id) {
        VipRecharge vipRechargeInfo = vipService.getVipRechargeInfoById(id);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vipRechargeInfo", vipRechargeInfo);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

}
