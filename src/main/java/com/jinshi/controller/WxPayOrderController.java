package com.jinshi.controller;

import com.jinshi.entity.WxPayOrder;
import com.jinshi.service.WxPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/wxPayOrder")
public class WxPayOrderController {

    @Autowired
    private WxPayOrderService wxPayOrderService;

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {

        return wxPayOrderService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(WxPayOrder record) {

        return wxPayOrderService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(WxPayOrder record) {

        return wxPayOrderService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public WxPayOrder selectByPrimaryKey(Integer id) {

        return wxPayOrderService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(WxPayOrder record) {

        return wxPayOrderService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(WxPayOrder record) {

        return wxPayOrderService.updateByPrimaryKey(record);
    }
}
