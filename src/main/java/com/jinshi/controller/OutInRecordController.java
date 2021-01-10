package com.jinshi.controller;

import com.jinshi.entity.OutInRecord;
import com.jinshi.service.OutInRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/outInRecord")
public class OutInRecordController {

    @Autowired
    private OutInRecordService outInRecordService;


    @ResponseBody
    @RequestMapping("connCamera")
    public void connCamera(int thandle){

    }

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {

        return outInRecordService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(OutInRecord record) {

        return outInRecordService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(OutInRecord record) {

        return outInRecordService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public OutInRecord selectByPrimaryKey(Integer id) {

        return outInRecordService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(OutInRecord record) {

        return outInRecordService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(OutInRecord record) {

        return outInRecordService.updateByPrimaryKey(record);
    }


    /**
     * 停车记录，根据用户ID
     */

    @RequestMapping("/selectParkRecord")
    public Map<String,Object> selectParkRecord(String memberId) {

        //查询停车记录
        Map<String,Object> result = outInRecordService.selectParkRecord(memberId);
        return result;
    }

}
