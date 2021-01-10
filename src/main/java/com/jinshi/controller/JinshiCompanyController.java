package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiCompany;
import com.jinshi.entity.JinshiDepartment;
import com.jinshi.service.JinshiCompanyService;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/jinshiCompany")
@Api(tags = "公司管理")
public class JinshiCompanyController {

    private static Logger logger = Logger.getLogger(JinshiCompanyController.class.getName());

    @Autowired
    private JinshiCompanyService jinshiCompanyService;

    @RequestMapping(value = "/selectCompanyAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "公司管理---查询所有")
    public String selectCompanyAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiCompany> res = jinshiCompanyService.selectCompanyForPage(pageNum,pageSize,parkId);
        int companyTotalRecords = jinshiCompanyService.getCompanyTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("companyData",res);
        resJo.put("companyTotalRecords",companyTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchCompany",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "公司管理---搜索")
    public String searchCompany(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        String parkIdStr = (String) jsonParam.get("parkId");
        List<JinshiCompany> res = jinshiCompanyService.searchCompany(keyWord,pageNum,pageSize,Integer.parseInt(parkIdStr));
//        int companyTotalRecords = jinshiCompanyService.getCompanyTotalRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("companyData",res);
//        resJo.put("companyTotalRecords",companyTotalRecords);
        return resJo.toJSONString();
    }

//    @RequestMapping(value = "/searchByCompany",method = RequestMethod.POST)
//    @ResponseBody
//    public String searchByCompany(@RequestBody JSONObject jsonParam){
//        logger.info(jsonParam.toJSONString());
//        Integer pageNum = (Integer) jsonParam.get("pageNum");
//        Integer pageSize = (Integer) jsonParam.get("pageSize");
//        String keyWord = (String) jsonParam.get("keyWord");
//        String parkIdStr = (String) jsonParam.get("parkId");
//        List<JinshiCompany> jinshiCompany1 = new ArrayList<>();
//        JSONObject resJo = new JSONObject();
//        try {
//            jinshiCompany1= jinshiCompanyService.searchCompany(keyWord, pageNum,pageSize,Integer.parseInt(parkIdStr));
//            resJo.put("companyData",jinshiCompany1);
//        }catch (Exception e){
//            e.printStackTrace();
//            return resJo.toJSONString();
//        }
//        logger.info(resJo.toJSONString());
//        return resJo.toJSONString();
//    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "公司管理---添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiCompany jinshiCompany = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCompany.class);
        return jinshiCompanyService.insert(jinshiCompany);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "公司管理---删除")
    public Integer deleteById(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return jinshiCompanyService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByCompany",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "公司管理---编辑")
    public Integer updateByCompany(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        JinshiCompany jinshiCompany = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCompany.class);
        return jinshiCompanyService.updateByPrimaryKey(jinshiCompany);
    }
    @CrossOrigin
    @RequestMapping(value = "/selects",method = RequestMethod.POST)
    @ResponseBody
    public String selects(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam);
        Integer id = (Integer) jsonParam.get("id");
        List<JinshiDepartment> jinshiDepartmentList = jinshiCompanyService.selects(id);
        JSONObject resJo = new JSONObject();
        resJo.put("companyData",jinshiDepartmentList);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/selectname",method = RequestMethod.POST)
    @ResponseBody
    public String selectname(@RequestBody JSONObject jsonParam){
        String parkId = (String) jsonParam.get("parkId");
        List<JinshiCompany> selectname = jinshiCompanyService.selectname(Integer.valueOf(parkId));
        JSONObject resJo = new JSONObject();
        resJo.put("companyData",selectname);
        return resJo.toJSONString();
    }
}
