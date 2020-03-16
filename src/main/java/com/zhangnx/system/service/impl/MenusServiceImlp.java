package com.zhangnx.system.service.impl;

import com.zhangnx.system.dao.MenuDao;
import com.zhangnx.system.pojo.Administrativedivision;
import com.zhangnx.system.pojo.Menus;
import com.zhangnx.system.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.EasyuiTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenusServiceImlp implements MenusService {

    @Autowired
    MenuDao menuDao;


    @Override
    public List<Menus> getExistMenuByRoleId(Integer roleId){
        return  menuDao.getExistMenuByRoleId(roleId);
    }

    @Override
    public List<Menus> getOptionalMenuByRoleId(Integer roleId) {
        return menuDao.getOptionalMenuByRoleId(roleId);
    }

    @Override
    public Integer getParentId(Integer menuId) {
        return menuDao.getParentId(menuId);
    }

    @Override
    public Integer haveParent(Integer menuId) {
        return menuDao.haveParent(menuId);
    }

    @Override
    public List<Menus> getMenuListByMenuIds(List<Integer> menuIds) {
        if (menuIds.size()<1){
            menuIds.add(-0);
        }
        return menuDao.getMenuListByMenuIds(menuIds);
    }

    @Override
    public List<Integer> getChildMenuByParentId(Integer menuId) {
        return menuDao.getChildMenuByParentId(menuId);
    }

    @Override
    public List<Menus> getMenuListByUserId(Integer userId) {
        return menuDao.getMenuListByUserId(userId);
    }

    @Override
    public List<Menus> getAllMenuList() {
        return menuDao.getAllMenuList();
    }

    @Override
    public int getExistByRoleIdMenuId(Integer menuId, Integer roleId) {
        return menuDao.getExistByRoleIdMenuId(menuId,roleId);
    }


    @Override
    public int addMenus(Menus menu){
        return menuDao.addMenu(menu);
    }

    @Override
    public int delMenus(Integer menuId){
        return menuDao.delMenu(menuId);
    }

    @Override
    public int editMenus(Menus menu){
        return menuDao.editMenu(menu);
    }

}
