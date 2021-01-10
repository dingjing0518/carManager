package com.jinshi.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/jinshiweb")
@RestController
@CrossOrigin
public class JinshiWebController {

    @RequestMapping(value = "/selectWeb",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectWeb(@RequestBody JSONObject jsonParam) {
        JSONObject json = new JSONObject();


        return json;
    }
}
