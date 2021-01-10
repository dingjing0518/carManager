package com.jinshi.service;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;

public interface JinshiCamerasSpareService {
    Integer updateCamerasSpare(JinshiCamerasSpare jinshiCamerasSpare);

    JinshiCamerasSpare selectCameraId(JinshiCamerasSpare jinshiCamerasSpare);

    JinshiCamerasSpare selectRealCameraId(JinshiCamerasSpare jinshiCamerasSpare);

    Integer insert(JinshiCamerasSpare jinshiCamerasSpare);

    Integer deleteByParkId(String jcCamerasId, Integer parkId);
}
