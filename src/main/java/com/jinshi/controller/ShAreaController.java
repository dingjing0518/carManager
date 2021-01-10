package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.ShArea;
import com.jinshi.service.ShAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/sharea")
@Api(tags = "全国地理信息")
public class ShAreaController {
    @Autowired
    private ShAreaService shAreaService;

    @CrossOrigin
    @RequestMapping(value = "/selects",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "全国地理信息---查询所有")
    public String selects(){
        List<ShArea> selects = shAreaService.selects();

        JSONObject resJo = new JSONObject();
        resJo.put("shareaData",selects);

        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/selectcity",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "全国地理信息---根据省份查询城市")
    public String selectcity(@RequestBody JSONObject jsonParam){
        String name = (String) jsonParam.get("name");
        List<List<ShArea>> list = new ArrayList<>();
        list.add(shAreaService.selectcity(name));
        JSONObject resJo = new JSONObject();
        resJo.put("shareaData",list);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/selectDistrict",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "全国地理信息---根据城市查询区县")
    public String selectDistrict(@RequestBody JSONObject jsonParam){
        String name = (String) jsonParam.get("name");
        List<List<ShArea>> list = new ArrayList<>();
        list.add(shAreaService.selectcity(name));
        JSONObject resJo = new JSONObject();
        resJo.put("shareaData",list);
        return resJo.toJSONString();
    }
}
