package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiParking;
import com.jinshi.entity.JinshiUserRole;
import com.jinshi.entity.JinshiWeb;
import com.jinshi.entity.User;
import com.jinshi.mapper.JinshiParkingMapper;
import com.jinshi.mapper.JinshiWebMapper;
import com.jinshi.mapper.UserMapper;
import com.jinshi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JinshiWebMapper jinshiWebMapper;

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Override
    public User Sel(int id){
        return userMapper.Sel(id);
    }

    @Override
    public Integer insert(User user) {
        return  userMapper.insert(user);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JinshiUserRole> selectUserForPage(Integer pageNum, Integer pageSize,Integer parkid) {
        List<JinshiUserRole> jinshiUserRoles = userMapper.selectUserForPage(pageNum, pageSize,parkid);
        List<JinshiWeb> jinshiWebs = jinshiWebMapper.selectAll();
        HashMap<Integer, String> integerStringHashMap = new HashMap<>();
        for (JinshiWeb jinshiWeb : jinshiWebs) {
            Integer id = jinshiWeb.getId();
            String title = jinshiWeb.getTitle();
            integerStringHashMap.put(id,title);
        }
        for(JinshiUserRole jinshiUserRole : jinshiUserRoles){
            String jsRolearray = jinshiUserRole.getJsRolearray();
            List<String> jsRoleArrayList = new ArrayList<>();
            if (jsRolearray==null) {
                jinshiUserRole.setJsRolearray("");
            } else {
                String[] split = jsRolearray.split(",");
                for (int i = 0; i < split.length; i++) {
                    int i1 = Integer.parseInt(split[i]);
                    String s = integerStringHashMap.get(i1);
                    jsRoleArrayList.add(s);
                }
                jinshiUserRole.setJsRolearray(jsRoleArrayList.toString());
            }
        }
        return jinshiUserRoles;
    }

    @Override
    public int getUserTotalRecords() {
        return userMapper.getUserTotalRecords();
    }

    @Override
    public List<User> searchUser(String keyWord, Integer pageNum, Integer pageSize,Integer parkid) {
        return userMapper.searchUser(keyWord,pageNum,pageSize,parkid);
    }

    @Override
    public String selectByUsername(String userName) {
        return userMapper.selectByUsername(userName);
    }

    @Override
    public String selectUsername(String username) {
        return userMapper.selectUsername(username);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer updatePassword(User user) {
        return userMapper.updatePassword(user);
    }

    @Override
    public User selectAllByUserName(String username) {
        return userMapper.selectAllByUserName(username);
    }

    @Override
    public List<User> selectUserAll(Integer pageNum, Integer pageSize, Integer parkId) {
        return userMapper.selectUserAll(pageNum,pageSize,parkId);
    }

    @Override
    public List<User> selectUser(Integer pageNum, Integer pageSize) {
        List<User> userList = userMapper.selectUser(pageNum, pageSize);
        for (User user : userList) {
            String roleName = user.getRoleName();
            if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)|| roleName.contains("代理商")) {
                continue;
            }
            List<JinshiParking> jinshiParkings = jinshiParkingMapper.selectParkByParkId(user.getParkid());
            if (jinshiParkings.size() > 0) {
                JinshiParking jinshiParking = jinshiParkings.get(0);
                user.setParkName(jinshiParking.getJpName());
            }
        }
        return userList;
    }

    @Override
    public List<User> selectByAgentId(Integer agentId) {
        List<User> userList = userMapper.selectByAgentId(agentId);
        for (User user : userList) {
            String roleName = user.getRoleName();
            if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)|| roleName.contains("代理商")) {
                continue;
            }
            List<JinshiParking> jinshiParkings = jinshiParkingMapper.selectParkByParkId(user.getParkid());
            if (jinshiParkings.size() > 0) {
                JinshiParking jinshiParking = jinshiParkings.get(0);
                user.setParkName(jinshiParking.getJpName());
            }
        }
        return userList;
    }

    @Override
    public int getUserTotalByAgentId(Integer agentId) {
        return userMapper.getUserTotalByAgentId(agentId);
    }

    @Override
    public List<User> selectByParkId(Integer parkId) {
        List<User> userList = userMapper.selectByParkId(parkId);
        for (User user : userList) {
            String roleName = user.getRoleName();
            if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)|| roleName.contains("代理商")) {
                continue;
            }
            List<JinshiParking> jinshiParkings = jinshiParkingMapper.selectParkByParkId(user.getParkid());
            if (jinshiParkings.size() > 0) {
                JinshiParking jinshiParking = jinshiParkings.get(0);
                user.setParkName(jinshiParking.getJpName());
            }
        }
        return userList;
    }

    @Override
    public int getUserTotalByParkId(Integer parkId) {
        return userMapper.getUserTotalByParkId(parkId);
    }
}
