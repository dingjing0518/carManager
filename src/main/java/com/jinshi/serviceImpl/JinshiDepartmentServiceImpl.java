package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiCompany;
import com.jinshi.entity.JinshiDepartment;
import com.jinshi.mapper.JinshiCompanyMapper;
import com.jinshi.mapper.JinshiDepartmentMapper;
import com.jinshi.service.JinshiDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JinshiDepartmentServiceImpl implements JinshiDepartmentService {

    @Autowired
    private JinshiDepartmentMapper jinshiDepartmentMapper;

    @Autowired
    private JinshiCompanyMapper jinshiCompanyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jinshiDepartmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JinshiDepartment record) {
        return jinshiDepartmentMapper.insertSelective(record);
    }

    @Override
    public int insertSelective(JinshiDepartment record) {
        return jinshiDepartmentMapper.insertSelective(record);
    }

    @Override
    public JinshiDepartment selectByPrimaryKey(Integer id) {
        return jinshiDepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<JinshiDepartment> selectJinshiDepartmentAll() {
        return jinshiDepartmentMapper.selectJinshiDepartmentAll();
    }

    @Override
    public int updateByPrimaryKeySelective(JinshiDepartment record) {
        return jinshiDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JinshiDepartment record) {
        return jinshiDepartmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<JinshiDepartment> selectJinshiDepartmentPage(Integer pageNum, Integer pageSize,Integer parkId) {
        List<JinshiDepartment> jinshiDepartments = jinshiDepartmentMapper.selectJinshiDepartmentPage(pageNum, pageSize, parkId);
        for (JinshiDepartment jinshiDepartment : jinshiDepartments) {
            JinshiCompany jinshiCompany = jinshiCompanyMapper.selectByPrimaryKey(jinshiDepartment.getJdCompanyid());
            if (null != jinshiCompany) {
                jinshiDepartment.setJsCompanyName(jinshiCompany.getJsName());
            }
        }
        return jinshiDepartments;
    }

    @Override
    public List<JinshiDepartment> searchJinshiDepartment(String keyWork, Integer pageNum, Integer pageSize,Integer parkId) {
        List<JinshiDepartment> jinshiDepartments = jinshiDepartmentMapper.searchJinshiDepartment(keyWork, pageNum, pageSize, parkId);
        for (JinshiDepartment jinshiDepartment : jinshiDepartments) {
            JinshiCompany jinshiCompany = jinshiCompanyMapper.selectByPrimaryKey(jinshiDepartment.getJdCompanyid());
            if (null != jinshiCompany) {
                jinshiDepartment.setJsCompanyName(jinshiCompany.getJsName());
            }
        }
        return jinshiDepartments;
    }

    @Override
    public int getaGentRecords(Integer parkId) {
        return jinshiDepartmentMapper.getaGentRecords(parkId);
    }

    @Override
    public JinshiDepartment selectByuserName(String username) {
        return jinshiDepartmentMapper.selectByuserName(username);
    }
}
