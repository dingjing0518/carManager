package com.jinshi.controller;

import com.jinshi.entity.JinshiParkSetting;
import com.jinshi.service.JinshiParkSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/JinshiParkSettingSetting")
public class JinshiParkSettingController {
    
    @Autowired
    private JinshiParkSettingService jinshiParkSettingService;

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {

        return jinshiParkSettingService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(JinshiParkSetting record) {

        return jinshiParkSettingService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(JinshiParkSetting record) {

        return jinshiParkSettingService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public JinshiParkSetting selectByPrimaryKey(Integer id) {

        return jinshiParkSettingService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(JinshiParkSetting record) {

        return jinshiParkSettingService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(JinshiParkSetting record) {

        return jinshiParkSettingService.updateByPrimaryKey(record);
    }
}
