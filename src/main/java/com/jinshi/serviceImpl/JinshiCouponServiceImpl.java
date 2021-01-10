package com.jinshi.serviceImpl;

import com.jinshi.entity.*;
import com.jinshi.mapper.*;
import com.jinshi.service.JinshiCouponService;
import com.jinshi.util.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JinshiCouponServiceImpl implements JinshiCouponService {

    @Autowired
    private JinshiCouponMapper jinshiCouponMapper;

    @Autowired
    private JinshiBusinessAccountMapper jinshiBusinessAccountMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Autowired
    private JinshiAreaMapper jinshiAreaMapper;

    @Override
    public List<JinshiCouponBo> selectCouponPage(Integer pageNum, Integer pageSize,Integer cpId) {
        List<JinshiCouponBo> list = new ArrayList<>();
        List<JinshiCoupon> jinshiCouponList = jinshiCouponMapper.selectCouponPage(pageNum, pageSize,cpId);
        for (JinshiCoupon jinshiCoupon : jinshiCouponList) {
            //过期删除优惠券
//            Date couponEndtime = jinshiCoupon.getCouponEndtime();
//            Date date = new Date();
//            long time = date.getTime();
//            long endtime = couponEndtime.getTime();
//            if (time>endtime){
//                deleteByPrimaryKey(jinshiCoupon.getCouponId());
//                break;
//            }
            JinshiCouponBo jinshiCouponBo = new JinshiCouponBo();
            jinshiCouponBo.setCouponId(jinshiCoupon.getCouponId());
            jinshiCouponBo.setCouponName(jinshiCoupon.getCouponName());
            jinshiCouponBo.setCouponType(jinshiCoupon.getCouponType());
            jinshiCouponBo.setCouponCount(jinshiCoupon.getCouponCount());
            jinshiCouponBo.setCouponStarttime(jinshiCoupon.getCouponStarttime());
            jinshiCouponBo.setCouponEndtime(jinshiCoupon.getCouponEndtime());
            jinshiCouponBo.setCouponCreatetime(jinshiCoupon.getCouponCreatetime());
            jinshiCouponBo.setJcgReliefAllmoney(jinshiCoupon.getJcgReliefAllmoney());
            jinshiCouponBo.setJcgReliefAlltime(jinshiCoupon.getJcgReliefAlltime());
            jinshiCouponBo.setJcgReliefRemainmoney(jinshiCoupon.getJcgReliefRemainmoney());
            jinshiCouponBo.setJcgReliefRemaintime(jinshiCoupon.getJcgReliefRemaintime());
            jinshiCouponBo.setCouponDataF(jinshiCoupon.getCouponDataF());


            //jinshiCouponBo.setCouponDataB(jinshiCoupon.getCouponDataB());
            String couponDataA = jinshiCoupon.getCouponDataA();
            if (null != couponDataA && !couponDataA.equals("null")&& !couponDataA.equals("")) {
                jinshiCouponBo.setCouponAllCount(Integer.parseInt(couponDataA));
                jinshiCouponBo.setCouponDataA(Integer.parseInt(couponDataA));
            }
            Integer couponBcId = jinshiCoupon.getCouponBcId();
            if (null != couponBcId) {
                JinshiBusinessAccount jinshiBusinessAccount = jinshiBusinessAccountMapper.selectByPrimaryKey(couponBcId);
                jinshiCouponBo.setCouponBcName(jinshiBusinessAccount.getBcName());
            }

            Integer couponAgentId = jinshiCoupon.getCouponAgentId();
            if (null != couponAgentId) {
                Agent agent = agentMapper.selectByPrimaryKey(couponAgentId);
                jinshiCouponBo.setCouponAgentName(agent.getUsername());
            }

            Integer couponParkingId = jinshiCoupon.getCouponParkingId();
            if (null != couponParkingId) {
                JinshiParking jinshiParking = jinshiParkingMapper.selectByPrimaryKey(couponParkingId);
                jinshiCouponBo.setCouponParkingName(jinshiParking.getJpName());
            }

            Integer couponAreaId = jinshiCoupon.getCouponAreaId();
            if (null != couponAreaId) {
                JinshiArea jinshiArea = jinshiAreaMapper.selectByPrimaryKey(couponAreaId);
                jinshiCouponBo.setCouponAreaName(jinshiArea.getAreaName());
            }
            list.add(jinshiCouponBo);
        }
        return list;
    }

    @Override
    public int getCouponRecords() {
        return jinshiCouponMapper.getCouponRecords();
    }

    @Override
    public List<JinshiCoupon> searchCoupon(String keyWord, Integer pageNum, Integer pageSize) {
        return jinshiCouponMapper.searchCoupon(keyWord,pageNum,pageSize);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiCouponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(JinshiCouponBo jinshiCouponBo) {
        JinshiCoupon jinshiCoupon = new JinshiCoupon();
        jinshiCoupon.setCouponName(jinshiCouponBo.getCouponName());
        jinshiCoupon.setCouponType(jinshiCouponBo.getJcgType().toString());

        jinshiCoupon.setCouponStarttime(jinshiCouponBo.getCouponStarttime());
        jinshiCoupon.setCouponEndtime(jinshiCouponBo.getCouponEndtime());
        jinshiCoupon.setCouponCreatetime(new Date());
        if (jinshiCouponBo.getCouponCount()!=null&&jinshiCouponBo.getCouponCount()!=0){
        jinshiCoupon.setCouponDataA(jinshiCouponBo.getCouponCount().toString());
        jinshiCoupon.setCouponCount(jinshiCouponBo.getCouponCount());
        }
        jinshiCoupon.setJcgReliefAllmoney(jinshiCouponBo.getJcgReliefAllmoney());
        jinshiCoupon.setJcgReliefAlltime(jinshiCouponBo.getJcgReliefAlltime());

        jinshiCoupon.setJcgReliefRemainmoney(jinshiCouponBo.getJcgReliefAllmoney());
        jinshiCoupon.setJcgReliefRemaintime(jinshiCouponBo.getJcgReliefAlltime());
        jinshiCoupon.setCouponDataF(jinshiCouponBo.getCouponDataF());
        //jinshiCoupon.setCouponDataB(jinshiCouponBo.getCouponDataB());

        String couponBcName = jinshiCouponBo.getCouponBcName();
        Integer couponParkingId = jinshiCouponBo.getParkId();
        if (null != couponBcName) {
            JinshiBusinessAccount jinshiBusinessAccount = jinshiBusinessAccountMapper.selectByBcName(couponBcName,couponParkingId);
            jinshiCoupon.setCouponBcId(jinshiBusinessAccount.getBcId());
        }

        String couponAgentName = jinshiCouponBo.getCouponAgentName();
//        Agent agent = agentMapper.selectByuserName(couponAgentName);
        if (null != couponAgentName) {
            jinshiCoupon.setCouponAgentId(Integer.parseInt(couponAgentName));
        }

        String couponParkingName = jinshiCouponBo.getCouponParkingName();
        if (null != couponParkingName && !couponParkingName.equals("")) {
            JinshiParking jinshiParking = jinshiParkingMapper.selectByJpName(couponParkingName);
            jinshiCoupon.setCouponParkingId(jinshiParking.getJpId());
        }

        String couponAreaName = jinshiCouponBo.getCouponAreaName();
        if (null != couponAreaName) {
            JinshiArea jinshiArea = jinshiAreaMapper.findByAreaName(couponAreaName,jinshiCouponBo.getParkId());
            jinshiCoupon.setCouponAreaId(jinshiArea.getId());
        }
        JinshiCoupon jc = jinshiCouponMapper.findJC(jinshiCoupon);
        JinshiCoupon byname = jinshiCouponMapper.findByname(jinshiCoupon);
        if (byname!=null){
            return 300;
        }
        if (jc == null){
             jinshiCouponMapper.insert(jinshiCoupon);
        }else {

            return 500;
        }

        return 200;

    }

    @Override
    public Integer updateByPrimaryKey(JinshiCouponBo jinshiCouponBo) {
        JinshiCoupon jinshiCoupon = new JinshiCoupon();
        jinshiCoupon.setCouponId(jinshiCouponBo.getCouponId());
        jinshiCoupon.setCouponName(jinshiCouponBo.getCouponName());
        jinshiCoupon.setCouponType(jinshiCouponBo.getCouponType());
        jinshiCoupon.setCouponDataA(String.valueOf(jinshiCouponBo.getCouponDataA()));
        jinshiCoupon.setCouponStarttime(jinshiCouponBo.getCouponStarttime());
        jinshiCoupon.setCouponEndtime(jinshiCouponBo.getCouponEndtime());
        jinshiCoupon.setCouponCreatetime(new Date());
        jinshiCoupon.setCouponCount(jinshiCouponBo.getCouponCount());
        jinshiCoupon.setJcgReliefRemainmoney(jinshiCouponBo.getJcgReliefAllmoney());
        jinshiCoupon.setJcgReliefAllmoney(jinshiCouponBo.getJcgReliefAllmoney());
        jinshiCoupon.setJcgReliefRemaintime(jinshiCouponBo.getJcgReliefAlltime());
        jinshiCoupon.setJcgReliefAlltime(jinshiCouponBo.getJcgReliefAlltime());
        jinshiCoupon.setCouponDataF(jinshiCouponBo.getCouponDataF());
        //jinshiCoupon.setCouponDataB(jinshiCouponBo.getCouponDataB());
        String couponParkingName = jinshiCouponBo.getCouponParkingName();
        if (null != couponParkingName) {
            JinshiParking jinshiParking = jinshiParkingMapper.selectByJpName(couponParkingName);
            jinshiCoupon.setCouponParkingId(jinshiParking.getJpId());
        }
        String couponBcName = jinshiCouponBo.getCouponBcName();
        Integer couponParkingId = jinshiCouponBo.getParkId();
        if (null != couponBcName) {
            JinshiBusinessAccount jinshiBusinessAccount = jinshiBusinessAccountMapper.selectByBcName(couponBcName,couponParkingId);
            jinshiCoupon.setCouponBcId(jinshiBusinessAccount.getBcId());
        }

        String couponAgentName = jinshiCouponBo.getCouponAgentName();
        if (null != couponAgentName) {
            Agent agent = agentMapper.selectByuserName(couponAgentName);
            jinshiCoupon.setCouponAgentId(agent.getId());
        }



        String couponAreaName = jinshiCouponBo.getCouponAreaName();
        if (null != couponAreaName) {
            JinshiArea jinshiArea = jinshiAreaMapper.findByAreaName(couponAreaName,jinshiCouponBo.getParkId());
            jinshiCoupon.setCouponAreaId(jinshiArea.getId());
        }

        return jinshiCouponMapper.updateByPrimaryKey(jinshiCoupon);
    }

    @Override
    public Integer updateCouponCount(JinshiCoupon jinshiCoupon) {
        return jinshiCouponMapper.updateCouponCount(jinshiCoupon);
    }

    @Override
    public JinshiCoupon selectByPrimaryKey(Integer jcId){
        return jinshiCouponMapper.selectByPrimaryKey(jcId);
    }
}
