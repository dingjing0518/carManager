package com.jinshi.service;

import com.jinshi.entity.JinshiDepartment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
public interface JinshiDepartmentService {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiDepartment record);

    int insertSelective(JinshiDepartment record);

    JinshiDepartment selectByPrimaryKey(Integer id);

    /***
     * 查询所有
     */
    List<JinshiDepartment> selectJinshiDepartmentAll();

    int updateByPrimaryKeySelective(JinshiDepartment record);

    int updateByPrimaryKey(JinshiDepartment record);
    /***
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<JinshiDepartment> selectJinshiDepartmentPage(Integer pageNum, Integer pageSize,Integer parkId);

    /***
     * 搜索
     * @param keyWork
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<JinshiDepartment> searchJinshiDepartment(@Param(value = "keyWork") String keyWork, @Param(value = "pageNum") Integer pageNum, @Param(value = "pageSize") Integer pageSize,@Param(value = "parkId") Integer parkId);

    int getaGentRecords(Integer parkId);

    JinshiDepartment selectByuserName(String username);
}