package com.zhangnx.system.controller;

import com.zhangnx.system.pojo.Administrativedivision;
import com.zhangnx.system.pojo.Nodes;
import com.zhangnx.system.service.AdministrativedivdsionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value ="/administrativedivdsionController")
public class AdministrativedivdsionController extends BaseController{

   @Autowired
   AdministrativedivdsionService admDivdService;

    @RequestMapping(value ="/getAllAdministrativedivisionList")
    @ResponseBody
    public List<Nodes> getAllAdministrativedivisionList(){
        List<Administrativedivision> admDivdList =admDivdService.getAllAdministrativedivisionList();
       return  getAdministrativedivisionListTree(admDivdList);

    }







}