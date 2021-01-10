package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.*;
import com.jinshi.mapper.*;
import com.jinshi.service.AgentService;
import com.jinshi.service.UserService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.ShiroConfig;
import com.jinshi.util.SortUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/testBoot")
@Api(tags = "用户管理")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private JinshiRoleMapper jinshiRoleMapper;

    @Autowired
    private JinshiWebMapper jinshiWebMapper;

    @Autowired
    private JinshiWebSubsMapper jinshiWebSubsMapper;

    @Autowired
    private AgentService agentService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }

    /**
     * 查询权限名称
     * @return
     */
    @RequestMapping(value = "/selectRolename",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户管理---查询权限名称")
    public JSONObject selectRolename() {
        JSONObject json = new JSONObject();
        List<JinshiRole>  roleList = jinshiRoleMapper.selectRolename();
        json.put("rolename",roleList);
        return  json;
    }


    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户管理---添加")
    public JSONObject insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JSONObject jsonObject = jsonParam.getJSONObject("setData");
        JSONObject json = new JSONObject();
        User user = new User();
        String username = (String) jsonObject.get("userName");
        String username1 = userService.selectUsername(username);
        if (username1 == null) {
            user.setUserName(username);
            user.setPassWord(ShiroConfig.stringMD5((String) jsonObject.get("passWord")));
            user.setRoleName((String) jsonObject.get("roleName"));
            user.setRealName((String) jsonObject.get("realName"));
            user.setProvince((String) jsonObject.get("province"));
            user.setCity((String) jsonObject.get("city"));
            user.setDistrict((String) jsonObject.get("district"));
            user.setPhoneNumber((String) jsonObject.get("phoneNumber"));
            user.setParkid((Integer) jsonParam.get("parkIdUser"));
            user.setAgentid((Integer) jsonObject.get("agentId"));
            userService.insert(user);
            json.put("code", "0");
            return json;
        } else {
            json.put("code", "1");
            return json;
        }
    }

    /**
     * 登录
     * 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
     * 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
     * @param userName
     * @param passWord
     * @return
     * @throws AuthenticationException
     */
    @PostMapping(value = "/login")
    @CrossOrigin
    @ResponseBody
    @ApiOperation(value = "用户管理---登陆")
    public Object login(@RequestParam(value = "userName",required = false) String userName,
                        @RequestParam(value = "passWord", required = false) String passWord ) throws AuthenticationException {
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        List<Object> list = new ArrayList<>();

        String passwords = ShiroConfig.encrypt(passWord);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passwords, true);
        try {
            subject.login(usernamePasswordToken);
            User user = (User) subject.getPrincipal();

            String roleName = userService.selectByUsername(userName);
            List<String> roleArray = jinshiRoleMapper.selectByRolename(roleName);
            List<JinshiWeb> jinshiWebList = jinshiWebMapper.selectData();
            for (String s : roleArray) {
                String sort = SortUtil.sort(s);
                String[] split = sort.split(",");
                for (int i=0;i<split.length;i++){
                    for (JinshiWeb jinshiWebs : jinshiWebList) {
                        if (split[i].equals(jinshiWebs.getId().toString())) {
                            List<JinshiWebSonAll> jinshiWebData = jinshiWebMapper.selectById(Integer.valueOf(split[i]));
                            list.add(jinshiWebData);
                            Integer subid = jinshiWebMapper.selectSub(Integer.valueOf(split[i]));
                            if (null!=subid) {
                                List<Subs> subsData = jinshiWebSubsMapper.selectByWebid(Integer.valueOf(split[i]));
                                for (JinshiWebSonAll jinshiWebDatum : jinshiWebData) {
                                    jinshiWebDatum.setSubss(subsData);
                                }
                            }
                        }
                    }
                }
            }
            result.put("info",list);
            result.put("user", user);
            result.put("roleName", roleName);
            result.put("code", "0");
            result.put("msg", "登录成功");
            logger.info("登录成功:  " + "userName:{},passWord:{} login success", userName, passWord);
        } catch (UnknownAccountException e) {
            //用户名不存在
            result.put("code", "1");
            result.put("msg", "用户名不存在");
            logger.error("用户名不存在:  " + "userName:{},passWord:{} login fail,error info is:{}", userName, passWord, e.getMessage());
        } catch (IncorrectCredentialsException e) {
            //密码错误
            result.put("code", "2");
            result.put("msg", "用户名或密码错误");
            logger.error("用户名或密码错误:  " + "userName:{},passWord:{} login fail,error info is:{}", userName, passWord, e.getMessage());
        }catch (AuthenticationException e) {
            //出现其他异常
            result.put("code", "-1");
            result.put("msg", e.getMessage());
            logger.error("userName:{},passWord:{} login fail,error info is:{}", userName, passWord, e.getMessage());
        }
        return result;
    }

    /**
     * 修改密码
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    @ApiOperation(value = "用户管理---修改密码")
    public JSONObject updatePassword(@RequestBody JSONObject jsonParam) {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        user.setUserName((String) jsonParam.get("userName"));
        user.setPassWord(ShiroConfig.stringMD5((String) jsonParam.get("passWord")));
        user.setRealName((String) jsonParam.get("realName"));
        userService.updatePassword(user);
        jsonObject.put("code","修改成功");
        return jsonObject;
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ApiOperation(value = "用户管理---删除")
    public Integer deleteByPrimaryKey(Integer id) {
        return userService.deleteByPrimaryKey(id);
    }

    /**
     * 用户管理查询所有，根据不同登陆账号显示不同权限内容
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectUserAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "用户管理---查询所有")
    public String selectUserAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        String agentId = (String) jsonParam.get("agentId");
        pageNum = (pageNum-1)*pageSize;
        String roleName = (String) jsonParam.get("roleName");
        List<JinshiUserRole> res = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Agent agent = agentService.selectByPrimaryKey(Integer.valueOf(agentId));
        int memberTotalRecords = 0;
        if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)) {
            userList = userService.selectUser(pageNum,pageSize);
            memberTotalRecords = userService.getUserTotalRecords();
        } else if (roleName.contains("代理商")) {
            userList = userService.selectByAgentId(Integer.parseInt(agentId));
            memberTotalRecords = userService.getUserTotalByAgentId(Integer.parseInt(agentId));
        } else if ("车场管理员".equals(roleName)) {
            userList = userService.selectByParkId(Integer.parseInt(parkId));
            memberTotalRecords = userService.getUserTotalByParkId(Integer.parseInt(parkId));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("userData",res);
        resJo.put("user",userList);
        resJo.put("userTotalRecords",memberTotalRecords);
        resJo.put("agent",agent);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户管理---搜索")
    public String searchUser(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        Integer parkid = GlobalVariable.parkId;
        List<User> res = userService.searchUser(keyWord,pageNum,pageSize,parkid);
        int memberTotalRecords = userService.getUserTotalRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("userData",res);
        resJo.put("userTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }


    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户管理---编辑")
    public int updateUser (@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JSONObject jsonObject = jsonParam.getJSONObject("setData");
        User user = new User();
        user.setId((Integer) jsonObject.get("id"));
        user.setUserName((String) jsonObject.get("userName"));
        user.setPassWord((ShiroConfig.stringMD5((String) jsonObject.get("passWord"))));
        user.setRealName((String) jsonObject.get("realName"));
        user.setRoleName((String) jsonObject.get("roleName"));
        user.setProvince((String) jsonObject.get("province"));
        user.setCity((String) jsonObject.get("city"));
        user.setDistrict((String) jsonObject.get("district"));
        user.setPhoneNumber((String) jsonObject.get("phoneNumber"));
        user.setParkid((Integer) jsonParam.get("parkIdUser"));
        user.setAgentid((Integer) jsonObject.get("agentId"));
        return userService.updateUser(user);
    }
}
