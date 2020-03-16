package com.zhangnx.system.controller;

import com.zhangnx.system.pojo.Administrativedivision;
import com.zhangnx.system.pojo.Menus;
import com.zhangnx.system.pojo.Nodes;
import com.zhangnx.system.service.AdministrativedivdsionService;
import com.zhangnx.system.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {
    private final Integer PARENTID = 0;

    @Autowired
    MenusService menusService;

    @Autowired
    AdministrativedivdsionService admDivdService;





    public List<Nodes> getMenuListTree(List<Menus> menusList,Integer pid){
        List<Nodes> nodesList = new ArrayList<Nodes>();
        for (Menus m : menusList) {
            Nodes nodes = new Nodes();
            nodes.setId(m.getMenuId());
            nodes.setText(m.getMenuName());
            nodes.setPid(m.getParentId());
            nodes.setIconCls(m.getIconCls());
            Map<String, Object> attr = new HashMap<String, Object>();
            attr.put("url", m.getUrl());
            attr.put("sort", m.getSort());
            attr.put("enable", m.getEnable());
            nodes.setAttributes(attr);
            nodesList.add(nodes);
        }

        Map<Integer,Nodes> parentMap=new HashMap<Integer, Nodes>();
        for (Nodes node:nodesList){
            parentMap.put(node.getId(),node);
        }
        List<Nodes> treeList = new ArrayList<Nodes>();
        for (Nodes node:nodesList) {
            if (pid.equals(node.getPid())){
                treeList.add(node);
            }else {
              node.getChildren().add(parentMap.get(node.getPid()));
            }
        }
        return treeList;
    }
















    public List<Nodes> getMenuListTree(List<Menus> menuList) {
        List<Integer> menuIds = new ArrayList<Integer>();
        for (Menus menus : menuList) {
            menuIds.addAll(getAllMenuParentId(menus.getMenuId()));
        }

        List<Menus> menusList = menusService.getMenuListByMenuIds(menuIds);

        List<Nodes> nodesList = new ArrayList<Nodes>();
        for (Menus m : menusList) {
            Nodes nodes = new Nodes();
            nodes.setId(m.getMenuId());
            nodes.setText(m.getMenuName());
            nodes.setPid(m.getParentId());
            nodes.setIconCls(m.getIconCls());
            Map<String, Object> attr = new HashMap<String, Object>();
            attr.put("url", m.getUrl());
            attr.put("sort", m.getSort());
            attr.put("enable", m.getEnable());
            nodes.setAttributes(attr);
            nodesList.add(nodes);
        }

        return listToTreeEasyUi(PARENTID, nodesList);
    }

    //根据菜单ID去寻找父级菜单ID
    public List<Integer> getAllMenuParentId(Integer menuId) {
        List<Integer> parentIdList = new ArrayList<Integer>();
        if (menusService.haveParent(menuId) < 1) {
            parentIdList.add(menuId);
            return parentIdList;
        }
        Integer parentId = menusService.getParentId(menuId);
        List<Integer> listParent = getAllMenuParentId(parentId);
        parentIdList.add(menuId);
        parentIdList.addAll(listParent);

        return parentIdList;
    }

    public List<Integer> getAllMenuChildreenId(Integer menuId) {
        List<Integer> lists = new ArrayList<Integer>();
        List<Integer> menusList = menusService.getChildMenuByParentId(menuId);
        for (Integer m : menusList) {
            lists.add(m);
            lists.addAll(getAllMenuChildreenId(m));
        }
        return lists;
    }

    private List<Nodes> listToTreeEasyUi(Integer parentId, List<Nodes> nodesList) {
        List<Nodes> rootNodes = new ArrayList<Nodes>();
        for (Nodes node : nodesList) {
            if (parentId.equals(node.getPid())) {
                rootNodes.add(node);
            }
        }
        for (Nodes node : rootNodes) {
            node.setChildren(listToTreeEasyUi(node.getId(), nodesList));
        }
        return rootNodes;
    }


    public List<Nodes> getAdministrativedivisionListTree(List<Administrativedivision> admDivdList) {

        List<Nodes> nodesList = new ArrayList<Nodes>();
        for (Administrativedivision adm : admDivdList) {
            Nodes nodes = new Nodes();
            nodes.setId(adm.getId());
            nodes.setText(adm.getAreaName());
            nodes.setPid(adm.getParentId());
            Map<String, Object> attr = new HashMap<String, Object>();
            attr.put("addr", adm.getAreaAddr());
            attr.put("sort", adm.getSort());
            attr.put("code", adm.getAreaCode());
            nodes.setAttributes(attr);
            nodesList.add(nodes);
        }
        return listToTreeEasyUi(PARENTID, nodesList);
    }

    public List<Integer> getAllAdministrativedivisionChildreenId(Integer admId) {
        List<Integer> lists = new ArrayList<Integer>();
        List<Integer> admList = admDivdService.getChildAdministrativedivisionByParentId(admId);
        for (Integer m : admList) {
            lists.add(m);
            lists.addAll(getAllAdministrativedivisionChildreenId(m));
        }
        return lists;
    }

}

