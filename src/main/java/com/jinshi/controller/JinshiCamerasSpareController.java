package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.bankPay.SuccessVo;
import com.jinshi.entity.JinshiCCBPay;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;
import com.jinshi.service.JinshiCamerasSpareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/JinshiCamerasSpare")
@CrossOrigin
@Controller
@Api(tags = "备用摄像机")
public class JinshiCamerasSpareController {

    private static Logger logger = Logger.getLogger(JinshiCamerasSpareController.class.getName());

    @Autowired
    private JinshiCamerasSpareService jinshiCamerasSpareService;

    /**
     * 根据parkId和编号修改备用摄像机的编号
     * @return
     */
    @PostMapping("/updateCamerasSpare")
    @ResponseBody
    @ApiOperation(value = "备用摄像机---根据parkId和编号修改备用摄像机的编号")
    public Integer updateCamerasSpare(@RequestBody JSONObject jsonParam) {
        logger.info("修改备用摄像机的编号----------------" + jsonParam.toJSONString());
        JinshiCamerasSpare jinshiCamerasSpare = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCamerasSpare.class);
        return jinshiCamerasSpareService.updateCamerasSpare(jinshiCamerasSpare);
    }

    /**
     * 摄像机编号确定(根据摄像机真实的返回内部设定的)
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectInternalCameraId",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "备用摄像机---摄像机编号确定(根据摄像机真实的返回内部设定的)")
    public JinshiCamerasSpare selectInternalCameraId(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiCamerasSpare jinshiCamerasSpare = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCamerasSpare.class);
        return jinshiCamerasSpareService.selectCameraId(jinshiCamerasSpare);
    }

    /**
     * 摄像机编号确定(根据内部设定的返回摄像机真实的)
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectRealCameraId",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "备用摄像机---摄像机编号确定(根据内部设定的返回摄像机真实的)")
    public JinshiCamerasSpare selectRealCameraId(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiCamerasSpare jinshiCamerasSpare = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCamerasSpare.class);
        return jinshiCamerasSpareService.selectRealCameraId(jinshiCamerasSpare);
    }
}
