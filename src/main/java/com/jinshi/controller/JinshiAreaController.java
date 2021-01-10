package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiArea;
import com.jinshi.service.JinshiAreaService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/jinshiArea")
@Api(tags = "区域管理")
public class JinshiAreaController {

    private static Logger logger = Logger.getLogger(JinshiAreaController.class.getName());

    @Autowired
    private JinshiAreaService jinshiAreaService;

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "区域管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return jinshiAreaService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insertJinshiArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "区域管理---添加")
    public Integer insertJinshiArea(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiArea jinshiArea = JSONObject.parseObject(jsonParam.toJSONString(), JinshiArea.class);
        return jinshiAreaService.insert(jinshiArea);
    }

    @RequestMapping(value = "/selectJinshiAreaAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "区域管理---查询所有")
    public String selectJinshiAreaAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiArea> res = jinshiAreaService.selectAreaForPage(pageNum,pageSize,parkId);
        int areaTotalRecords = jinshiAreaService.getAreaTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("jinshiAreaData",res);
        resJo.put("jinshiAreaTotalRecords",areaTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "区域管理---搜索")
    public String searchArea(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiArea> res = jinshiAreaService.searchArea(keyWord,pageNum,pageSize,parkId);
        int areaTotalRecords = jinshiAreaService.getAreaTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("jinshiAreaData",res);
        resJo.put("jinshiAreaTotalRecords",areaTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/updateByArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "区域管理---编辑")
    public Integer updateByArea(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        JinshiArea jinshiArea = JSONObject.parseObject(jsonParam.toJSONString(), JinshiArea.class);
        return jinshiAreaService.updateByArea(jinshiArea);
    }

    @RequestMapping(value = "/selectAreaNameAll",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String selectAreaNameAll(@RequestParam("parkId") String parkId){
        List<JinshiArea> res = jinshiAreaService.selectAreaNameAll(Integer.valueOf(parkId));
        JSONObject resJo = new JSONObject();
        resJo.put("areaNameData",res);
        return resJo.toJSONString();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/selectByParkIdAndAreaName", method = RequestMethod.POST)
    public JinshiArea selectByParkIdAndAreaName(@RequestBody JSONObject jsonParam) {
        Integer parkId = (Integer) jsonParam.get("parkId");
        String areaName = (String) jsonParam.get("areaName");
        JinshiArea jinshiArea = jinshiAreaService.selectByJcArea(areaName,parkId);
        return jinshiArea;
    }

    /**
     * 根据车场id查询所属区域
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @GetMapping("/selectAllArea")
    public List<JinshiArea> selectAllArea(Integer parkId) {
        return jinshiAreaService.selectAllArea(parkId);
    }
}
