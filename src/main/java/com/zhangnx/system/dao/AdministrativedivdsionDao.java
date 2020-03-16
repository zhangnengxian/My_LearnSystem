package com.zhangnx.system.dao;

import com.zhangnx.system.pojo.Administrativedivision;

import java.util.List;

public interface AdministrativedivdsionDao {

    List<Administrativedivision> getAllAdministrativedivisionList();


    List<Integer> getChildAdministrativedivisionByParentId(Integer admId);
}
