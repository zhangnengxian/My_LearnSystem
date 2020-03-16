package com.zhangnx.system.dao;

import com.zhangnx.system.pojo.Menus;

import java.util.List;

//@Repository("MenuDao ")
public interface MenuDao {

    List<Menus> getExistMenuByRoleId(Integer roleId);

    List<Menus> getOptionalMenuByRoleId(Integer roleId);

    int addMenu(Menus menu);
    int editMenu(Menus menu);
    int delMenu(Integer menuId);

    Integer getParentId(Integer menuId);

    Integer haveParent(Integer menuId);

    List<Menus> getMenuListByMenuIds(List<Integer> menuIds);

    List<Integer> getChildMenuByParentId(Integer menuId);

    List<Menus> getMenuListByUserId(Integer userId);

    List<Menus> getAllMenuList();

    int getExistByRoleIdMenuId(Integer menuId, Integer roleId);
}
