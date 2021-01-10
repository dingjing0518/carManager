package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.bo.JinshiLincenseGroupBo;
import com.jinshi.service.JinshiLincenseGroupService;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jinshiLincenseGroup")
@CrossOrigin
@Api(tags = "车牌组管理")
public class JinshiLincenseGroupController {
    private static Logger logger = Logger.getLogger(JinshiLincenseGroupController.class.getName());

    @Autowired
    private JinshiLincenseGroupService jinshiLincenseGroupService;
    
    /***
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectLincenseGroupAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车牌组管理---查询所有")
    public String selectLincenseGroupAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        Integer parkId = (Integer) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiLincenseGroupBo> res= jinshiLincenseGroupService.selectLincenseGroupPage(pageNum,pageSize,parkId);
        int getLincenseGroupRecords = jinshiLincenseGroupService.getLincenseGroupRecords(parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lincenseGroupData",res);
        jsonObject.put("lincenseGroupRecords",getLincenseGroupRecords);
        return jsonObject.toJSONString();
    }

    /***
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchLincenseGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车牌组管理---搜索")
    public String searchLincenseGroup(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        Integer parkId = (Integer) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiLincenseGroup> res = jinshiLincenseGroupService.searchLincenseGroup(keyWord,pageNum,pageSize,parkId);
        int getLincenseGroupRecords = jinshiLincenseGroupService.getLincenseGroupRecordsSearch(keyWord,parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseGroupData",res);
        resJo.put("lincenseGroupRecords",getLincenseGroupRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车牌组管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return jinshiLincenseGroupService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车牌组管理---添加")
    public JSONObject insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiLincenseGroup jinshiLincenseGroup  = JSONObject.parseObject(jsonParam.toJSONString(), JinshiLincenseGroup.class);
        JinshiLincenseGroup jinshiLincenseGroup1 = jinshiLincenseGroupService.selectAllByJsNumber(jinshiLincenseGroup);
        JSONObject jsonObject = new JSONObject();
        if (jinshiLincenseGroup1 != null) {
            jsonObject.put("msg", "编号重复，请重新输入");//同车场同区域编号不能相同
        } else {
            jinshiLincenseGroupService.insert(jinshiLincenseGroup);
            jsonObject.put("msg", "添加成功");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车牌组管理---编辑")
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiLincenseGroup jinshiLincenseGroup  = JSONObject.parseObject(jsonParam.toJSONString(), JinshiLincenseGroup.class);
        return jinshiLincenseGroupService.updateByPrimaryKey(jinshiLincenseGroup);
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    @CrossOrigin
    public List<JinshiLincenseGroup> selectAll() {
        return jinshiLincenseGroupService.selectALl();
    }

    @PostMapping(value = "/selectAllByJsNumber")
    @ResponseBody
    @CrossOrigin
    public JinshiLincenseGroup selectAllByJsNumber(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiLincenseGroup jinshiLincenseGroup  = JSONObject.parseObject(jsonParam.toJSONString(), JinshiLincenseGroup.class);
        return jinshiLincenseGroupService.selectAllByJsNumber(jinshiLincenseGroup);
    }

    @PostMapping(value = "/selectByPrimaryKey")
    @ResponseBody
    @CrossOrigin
    public JinshiLincenseGroup selectByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer lgId = (Integer) jsonParam.get("jsLgId");
        return jinshiLincenseGroupService.selectByPrimaryKey(lgId);
    }

    /**
     * 按车场区域查询车牌组
     * @param jsonParam
     * @return
     */
    @PostMapping(value = "/selectByParkIdAndAreaId")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车牌组管理---按车场区域查询车牌组")
    public List<JinshiLincenseGroup> selectByParkIdAndAreaId(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiLincenseGroup jinshiLincenseGroup = JSONObject.parseObject(jsonParam.toJSONString(), JinshiLincenseGroup.class);
        return jinshiLincenseGroupService.selectByParkIdAndAreaId(jinshiLincenseGroup);
    }
}
