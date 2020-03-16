package com.zhangnx.system.service.impl;

import com.zhangnx.system.dao.AdministrativedivdsionDao;
import com.zhangnx.system.pojo.Administrativedivision;
import com.zhangnx.system.service.AdministrativedivdsionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrativedivdsionServiceImpl implements AdministrativedivdsionService {

    @Autowired
    AdministrativedivdsionDao admDivDao;

    @Override
    public List<Administrativedivision> getAllAdministrativedivisionList() {
        return admDivDao.getAllAdministrativedivisionList();
    }

    @Override
    public List<Integer> getChildAdministrativedivisionByParentId(Integer admId) {
        return admDivDao.getChildAdministrativedivisionByParentId(admId);
    }
}
