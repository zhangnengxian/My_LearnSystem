package com.zhangnx.system.service;

import com.zhangnx.system.pojo.Administrativedivision;

import java.util.List;

public interface AdministrativedivdsionService {

  List<Administrativedivision>  getAllAdministrativedivisionList();

  List<Integer> getChildAdministrativedivisionByParentId(Integer admId);
}
