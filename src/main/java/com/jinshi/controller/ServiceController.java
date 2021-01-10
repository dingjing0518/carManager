package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Service;
import com.jinshi.service.ServiceService;
import com.jinshi.util.QianYiCameraUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/service")
public class ServiceController {

    private static Logger logger = LogManager.getLogger(QianYiCameraUtil.class.getName());

    @Autowired
    private ServiceService serviceService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/selectServiceAll")
    public String selectServiceAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<Service> res = serviceService.selectServcieForPage(pageNum,pageSize);
        int serviceTotalRecords = serviceService.getServiceTotalRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("serviceData",res);
        resJo.put("serviceTotalRecords",serviceTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/selectServiceForwx")
    public List<Service> selectServiceForwx(){
        logger.info("获取服务信息发送给小程序");
        List<Service> service = serviceService.selectServiceAll();
        return service;
    }

    @CrossOrigin
    @RequestMapping(value = "/searchService",method = RequestMethod.POST)
    @ResponseBody
    public String searchService(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<Service> res = serviceService.searchService(keyWord,pageNum,pageSize);
        int serviceTotalRecords = serviceService.getServiceTotalRecordsSearch(keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("serviceData",res);
        resJo.put("serviceTotalRecords",serviceTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/searchByServiceName",method = RequestMethod.POST)
    @ResponseBody
    public String searchByServiceName(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam.toJSONString());
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String keyWord = (String) jsonParam.get("keyWord");
        List<Service> service1 = new ArrayList<>();
        Integer serviceTotalRecords = null;
        JSONObject resJo = new JSONObject();
        try {
            service1 = serviceService.searchService(keyWord,pageNum,pageSize);
            serviceTotalRecords = serviceService.getServiceTotalRecordsSearch(keyWord);
            resJo.put("serviceData",service1);
            resJo.put("serviceTotalRecords" , serviceTotalRecords);
        }catch (Exception e){
            e.printStackTrace();
            return resJo.toJSONString();
        }
        logger.info(resJo.toJSONString());
        return resJo.toJSONString();
    }
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return serviceService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        Service service = JSONObject.parseObject(jsonParam.toJSONString(), Service.class);
        return serviceService.insert(service);
    }

    @RequestMapping(value = "/updateByService",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateByService(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam.toJSONString());
        Service service = JSONObject.parseObject(jsonParam.toJSONString(), Service.class);
        return serviceService.updateByPrimaryKey(service);
    }





    @RequestMapping("/insertSelective")
    public Integer insertSelective(Service record) {

        return serviceService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public Service selectByPrimaryKey(Integer id) {

        return serviceService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(Service record) {

        return serviceService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(Service record) {

        return serviceService.updateByPrimaryKey(record);
    }

}
