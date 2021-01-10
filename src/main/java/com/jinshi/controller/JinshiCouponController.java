package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiCoupon;
import com.jinshi.entity.JinshiCouponBo;
import com.jinshi.mapper.JinshiCouponMapper;
import com.jinshi.service.JinshiCouponService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JinshiCoupon")
@CrossOrigin
@Api(tags = "优惠券管理")
public class JinshiCouponController {
    private static Logger logger = Logger.getLogger(JinshiCouponController.class.getName());
    @Autowired
    private JinshiCouponService jinshiCouponService;

    @Autowired
    private JinshiCouponMapper jinshiCouponMapper;

    /***
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectCouponAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "优惠券管理---查询所有")
    public String selectCouponAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiCouponBo> res= jinshiCouponService.selectCouponPage(pageNum,pageSize, parkId);
        int CouponRecords = jinshiCouponService.getCouponRecords();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CouponData",res);
        jsonObject.put("CouponRecords",CouponRecords);
        return jsonObject.toJSONString();
    }

    /***
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchCoupon",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "优惠券管理---搜索")
    public String searchCoupon(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiCoupon> res = jinshiCouponService.searchCoupon(keyWord,pageNum,pageSize);
        int couponRecords = jinshiCouponService.getCouponRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("CouponData",res);
        resJo.put("CouponRecords",couponRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "优惠券管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return jinshiCouponService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "优惠券管理---添加")
    public Integer insert(@RequestBody JinshiCouponBo jinshiCouponBo) {
        return jinshiCouponService.insert(jinshiCouponBo);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "优惠券管理---编辑")
    public Integer updateByPrimaryKey(@RequestBody JinshiCouponBo jinshiCouponBo) {
        return jinshiCouponService.updateByPrimaryKey(jinshiCouponBo);
    }

    /**
     * 充值
     * @param jinshiCouponBo
     * @return
     */
    @RequestMapping(value = "/updateCouponCount",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "优惠券管理---充值")
    public Integer updateCouponCount(@RequestBody JinshiCouponBo jinshiCouponBo) {
        Integer couponCount = jinshiCouponBo.getCouponCount();
        Integer jcgType = jinshiCouponBo.getJcgType();

        JinshiCoupon jinshiCoupon = jinshiCouponService.selectByPrimaryKey(jinshiCouponBo.getCouponId());
        if (jcgType==0||jcgType==3) {
            Integer couponCount1 = jinshiCoupon.getCouponCount();
            Integer res = couponCount + couponCount1;
            jinshiCoupon.setCouponCount(res);
            String couponDataA = jinshiCoupon.getCouponDataA();
            int i = Integer.parseInt(couponDataA);
            Integer resDataA = couponCount + i;
            jinshiCoupon.setCouponDataA(resDataA.toString());
        }
        if (jcgType==1){
            String jcgReliefAlltime = jinshiCouponBo.getJcgReliefAlltime();
            String jcgReliefAlltime1 = jinshiCoupon.getJcgReliefAlltime();
            String jcgReliefRemaintime = jinshiCoupon.getJcgReliefRemaintime();
            Integer alltime=Integer.valueOf(jcgReliefAlltime)+Integer.valueOf(jcgReliefAlltime1);
            Integer remaintime = Integer.valueOf(jcgReliefAlltime)+Integer.valueOf(jcgReliefRemaintime);
            jinshiCoupon.setJcgReliefRemaintime(remaintime.toString());
            jinshiCoupon.setJcgReliefAlltime(alltime.toString());
        }
        if (jcgType==2){
            String jcgReliefAllmoney = jinshiCouponBo.getJcgReliefAllmoney();
            String jcgReliefAllmoney1 = jinshiCoupon.getJcgReliefAllmoney();
            String jcgReliefRemainmoney = jinshiCoupon.getJcgReliefRemainmoney();
            Integer allmoney = Integer.valueOf(jcgReliefAllmoney)+Integer.valueOf(jcgReliefAllmoney1);
            Integer remainmoney = Integer.valueOf(jcgReliefAllmoney)+Integer.valueOf(jcgReliefRemainmoney);
            jinshiCoupon.setJcgReliefAllmoney(allmoney.toString());
            jinshiCoupon.setJcgReliefRemainmoney(remainmoney.toString());
        }
        return jinshiCouponMapper.updateByPrimaryKeySelective(jinshiCoupon);
    }
}
