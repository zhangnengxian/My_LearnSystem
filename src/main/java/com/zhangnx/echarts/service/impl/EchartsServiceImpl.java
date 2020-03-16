package com.zhangnx.echarts.service.impl;

import com.zhangnx.echarts.dao.EchartsDao;
import com.zhangnx.echarts.pojo.Echarts;
import com.zhangnx.echarts.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    EchartsDao echartsDao;

    @Override
    public List<Echarts> getlearnRate() {
        return echartsDao.getlearnRate();
    }
}
