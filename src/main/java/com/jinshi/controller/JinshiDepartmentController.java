package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiDepartment;
import com.jinshi.service.JinshiDepartmentService;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/JinshiDepartment")
@CrossOrigin
@Api(tags = "部门管理")
public class JinshiDepartmentController {
    private static Logger logger = Logger.getLogger(JinshiDepartmentController.class.getName());
    @Autowired
    private JinshiDepartmentService jinshiDepartmentService;

    /***
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectJinshiDepartmentAll")
    @ResponseBody
    @ApiOperation(value = "部门管理---查询所有")
    public String selectJinshiDepartmentAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiDepartment> res= jinshiDepartmentService.selectJinshiDepartmentPage(pageNum,pageSize,parkId);
        int getaGentRecords = jinshiDepartmentService.getaGentRecords(parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("JinshiDepartmenttData",res);
        jsonObject.put("JinshiDepartmentRecords",getaGentRecords);

        return jsonObject.toJSONString();
    }
    /***
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchJinshiDepartment",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "部门管理---搜索")
    public String searchJinshiDepartment(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiDepartment> res = jinshiDepartmentService.searchJinshiDepartment(keyWord,pageNum,pageSize,parkId);
        int memberTotalRecords = jinshiDepartmentService.getaGentRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("JinshiDepartmenttData",res);
        resJo.put("JinshiDepartmentRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "部门管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam){
        Integer id = (Integer) jsonParam.get("id");
        return jinshiDepartmentService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "部门管理---添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiDepartment jinshiDepartment = JSONObject.parseObject(jsonParam.toJSONString(), JinshiDepartment.class);
        return jinshiDepartmentService.insert(jinshiDepartment);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(JinshiDepartment record){

        return jinshiDepartmentService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public JinshiDepartment selectByPrimaryKey(Integer id){

        return jinshiDepartmentService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "部门管理---编辑")
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        JinshiDepartment jinshiDepartment = JSONObject.parseObject(jsonParam.toJSONString(), JinshiDepartment.class);
        return jinshiDepartmentService.updateByPrimaryKey(jinshiDepartment);
    }
    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(JinshiDepartment record){

        return jinshiDepartmentService.updateByPrimaryKeySelective(record);
    }

}
