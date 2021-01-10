package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;
import com.jinshi.mapper.JinshiCamerasSpareMapper;
import com.jinshi.service.JinshiCamerasSpareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JinshiCamerasSpareServiceImpl implements JinshiCamerasSpareService {

    @Autowired
    private JinshiCamerasSpareMapper jinshiCamerasSpareMapper;

    @Override
    public Integer updateCamerasSpare(JinshiCamerasSpare jinshiCamerasSpare) {
        return jinshiCamerasSpareMapper.updateCamerasSpare(jinshiCamerasSpare);
    }

    @Override
    public JinshiCamerasSpare selectCameraId(JinshiCamerasSpare jinshiCamerasSpare) {
        return jinshiCamerasSpareMapper.selectCameraId(jinshiCamerasSpare);
    }

    @Override
    public JinshiCamerasSpare selectRealCameraId(JinshiCamerasSpare jinshiCamerasSpare) {
        return jinshiCamerasSpareMapper.selectRealCameraId(jinshiCamerasSpare);
    }

    @Override
    public Integer insert(JinshiCamerasSpare jinshiCamerasSpare) {
        return jinshiCamerasSpareMapper.insert(jinshiCamerasSpare);
    }

    @Override
    public Integer deleteByParkId(String jcCamerasId, Integer parkId) {
        return jinshiCamerasSpareMapper.deleteByParkId(jcCamerasId,parkId);
    }
}
