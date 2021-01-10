package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiCompany;
import com.jinshi.entity.JinshiDepartment;
import com.jinshi.mapper.JinshiCompanyMapper;
import com.jinshi.mapper.JinshiDepartmentMapper;
import com.jinshi.service.JinshiCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiCompanyServiceImpl implements JinshiCompanyService {

    @Autowired
    private JinshiCompanyMapper jinshiCompanyMapper;

    @Autowired
    private JinshiDepartmentMapper jinshiDepartmentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jinshiCompanyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JinshiCompany jinshiCompany) {

        return jinshiCompanyMapper.insert(jinshiCompany);
//        JinshiDepartment jinshiDepartment = new JinshiDepartment();
//        jinshiDepartment.setParkId(jinshiCompany.getParkId());
//        jinshiDepartment.setAgentId(jinshiCompany.getAgentId());
//        jinshiDepartment.setJdName(jinshiCompany.getJsDepartment());
//        jinshiDepartment.setJdUsername(jinshiCompany.getJsUsername());
//        jinshiDepartment.setJdPhone(jinshiCompany.getJsPhone());
//        jinshiDepartment.setJdCompanyid(jinshiCompany.getId());
//        return jinshiDepartmentMapper.insert(jinshiDepartment);
    }

    @Override
    public List<JinshiCompany> selectCompanyForPage(Integer pageNum, Integer pageSize,Integer parkId) {
        return jinshiCompanyMapper.selectCompanyForPage(pageNum,pageSize,parkId);
    }

    @Override
    public int getCompanyTotalRecords(Integer parkId) {
        return jinshiCompanyMapper.getCompanyTotalRecords(parkId);
    }

    @Override
    public List<JinshiCompany> searchCompany(String keyWord, Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiCompanyMapper.searchCompany(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public int updateByPrimaryKey(JinshiCompany jinshiCompany) {
        return jinshiCompanyMapper.updateByPrimaryKey(jinshiCompany);
    }

    @Override
    public List<JinshiDepartment> selects(Integer id) {
        return jinshiDepartmentMapper.selects(id);
    }

    @Override
    public List<JinshiCompany> selectname(Integer parkId) {
        return jinshiCompanyMapper.selectname(parkId);
    }
}
