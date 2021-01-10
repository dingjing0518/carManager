package com.jinshi.controller;


import com.jinshi.util.GlobalVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap map){
        map.addAttribute("name","values");
        map.addAttribute("uname","uvalues");
        return "index";
    }

    @RequestMapping(value = "/inner2",method = RequestMethod.GET)
    public String innerTo(ModelMap map){
        return "inner2";
    }

    @RequestMapping(value = "/inner",method = RequestMethod.GET)
    public String inner(ModelMap map){
        return "inner";
    }

    @RequestMapping(value = "/finance",method = RequestMethod.GET)
    public String finance(ModelMap map){
        return "finance";
    }

    @RequestMapping(value = "/number",method = RequestMethod.GET)
    public String number(ModelMap map){
        return "number";
    }

    @RequestMapping(value = "/outIn",method = RequestMethod.GET)
    public String outIn(ModelMap map){
        return "outIn";
    }

    @RequestMapping(value = "/fuwu",method = RequestMethod.GET)
    public String fuwu(ModelMap map){
        return "fuwu";
    }

    @RequestMapping(value = "/baimingdan",method = RequestMethod.GET)
    public String baimingdan(ModelMap baimingdan){
        return "baimingdan";
    }

    @RequestMapping(value = "/heimingdan",method = RequestMethod.GET)
    public String heimingdan(ModelMap map){
        return "heimingdan";
    }

    @RequestMapping(value = "/park",method = RequestMethod.GET)
    public String park(ModelMap map){
        return "park";
    }

    @RequestMapping(value = "/agent",method = RequestMethod.GET)
    public String agent(ModelMap map){
        return "agent";
    }

    @RequestMapping(value = "/rizhi",method = RequestMethod.GET)
    public String rizhi(ModelMap map){
        return "rizhi";
    }

    @RequestMapping(value = "/setting",method = RequestMethod.GET)
    public String setting(ModelMap map){
        return "setting";
    }

    @RequestMapping(value = "/setting1",method = RequestMethod.GET)
    public String settinga(Model map){
        map.addAttribute("parkName",GlobalVariable.parkName);
        map.addAttribute("parkNumber",GlobalVariable.parkNumber);
        map.addAttribute("parkPlaceNumber",GlobalVariable.parkPlaceNumber);
        map.addAttribute("parkCameraBrand",GlobalVariable.parkCameraBrand);
        map.addAttribute("parkPicturePath",GlobalVariable.parkPicturePath);
        return "setting1";
    }

    @RequestMapping(value = "/setting3",method = RequestMethod.GET)
    public String settingb(Model map){
        map.addAttribute("blueLicenseFreeTime",GlobalVariable.blueLicenseFreeTime);
        map.addAttribute("blueLicenseFirstTime",GlobalVariable.blueLicenseFirstTime);
        map.addAttribute("blueLincenseFirstCharge",GlobalVariable.blueLincenseFirstCharge);
        map.addAttribute("blueLincenseFollowTime",GlobalVariable.blueLincenseFollowTime);
        map.addAttribute("blueLincenseFollowCharge",GlobalVariable.blueLincenseFollowCharge);
        map.addAttribute("blueLincenseAllDayLimit",GlobalVariable.blueLincenseAllDayLimit);
        map.addAttribute("blueLincensePayAdvanceOutTime",GlobalVariable.blueLincensePayAdvanceOutTime);

        map.addAttribute("yellowLicenseFreeTime",GlobalVariable.yellowLicenseFreeTime);
        map.addAttribute("yellowLicenseFirstTime",GlobalVariable.yellowLicenseFirstTime);
        map.addAttribute("yellowLincenseFirstCharge",GlobalVariable.yellowLincenseFirstCharge);
        map.addAttribute("yellowLincenseFollowTime",GlobalVariable.yellowLincenseFollowTime);
        map.addAttribute("yellowLincenseFollowCharge",GlobalVariable.yellowLincenseFollowCharge);
        map.addAttribute("yellowLincenseAllDayLimit",GlobalVariable.yellowLincenseAllDayLimit);
        map.addAttribute("yellowLincensePayAdvanceOutTime",GlobalVariable.yellowLincensePayAdvanceOutTime);

        map.addAttribute("greenLicenseFreeTime",GlobalVariable.greenLicenseFreeTime);
        map.addAttribute("greenLicenseFirstTime",GlobalVariable.greenLicenseFirstTime);
        map.addAttribute("greenLincenseFirstCharge",GlobalVariable.greenLincenseFirstCharge);
        map.addAttribute("greenLincenseFollowTime",GlobalVariable.greenLincenseFollowTime);
        map.addAttribute("greenLincenseFollowCharge",GlobalVariable.greenLincenseFollowCharge);
        map.addAttribute("greenLincenseAllDayLimit",GlobalVariable.greenLincenseAllDayLimit);
        map.addAttribute("greenLincensePayAdvanceOutTime",GlobalVariable.greenLincensePayAdvanceOutTime);

        return "setting3";
    }
}
