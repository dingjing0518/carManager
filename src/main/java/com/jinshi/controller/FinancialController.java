package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Financial;
import com.jinshi.service.FinancialService;
import com.jinshi.util.QianYiCameraUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/financial")
public class FinancialController {
    private static Logger logger = Logger.getLogger(FinancialController.class.getName());
    @Autowired
    private FinancialService financialService;

    @CrossOrigin
    @RequestMapping(value ="/selectFinancialAll",method = RequestMethod.POST)
    @ResponseBody
    public String selectFinancialAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<Financial> res = financialService.selectFinancialForPage(pageNum,pageSize);
        int memberTotalRecords = financialService.getFinancialRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("FinancialData",res);
        resJo.put("FinancialRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchFinancial",method = RequestMethod.POST)
    @ResponseBody
    public String searchFinancial(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<Financial> res = financialService.searchFinancial(keyWord,pageNum,pageSize);
        int memberTotalRecords = financialService.getFinancialRecordsSearch(keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("FinancialData",res);
        resJo.put("FinancialRecords",memberTotalRecords);
        return resJo.toJSONString();
    }
    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey( Integer id) {

        return financialService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        Financial financial = JSONObject.parseObject(jsonParam.toJSONString(), Financial.class);
        return financialService.insert(financial);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(Financial record) {

        return financialService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public Financial selectByPrimaryKey(Integer id) {

        return financialService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(Financial record) {

        return financialService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(Financial record) {

        return financialService.updateByPrimaryKey(record);
    }
}
