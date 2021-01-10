package com.jinshi.service;

import com.jinshi.entity.JinshiParkingSetup;

public interface JinshiParkingSetupService {
    Integer updateSetup(JinshiParkingSetup jinshiParkingSetup);

    JinshiParkingSetup selectParkSetup(Integer parkId);

    Integer updateSetupHoliday(Integer type1);

    Integer updateOpenMode(JinshiParkingSetup jinshiParkingSetup);
}
