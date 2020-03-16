package com.zhangnx.system.service;
import com.zhangnx.system.pojo.Menus;

import java.util.List;


public interface MenusService {

    int addMenus(Menus menu);
    int editMenus(Menus menu);
    int delMenus(Integer menuId);

    List<Menus> getExistMenuByRoleId(Integer roleId);

    List<Menus> getOptionalMenuByRoleId(Integer roleId);

    Integer getParentId(Integer menuId);

    Integer haveParent(Integer menuId);

    List<Menus> getMenuListByMenuIds(List<Integer> menuIds);

    List<Integer> getChildMenuByParentId(Integer menuId);

    List<Menus> getMenuListByUserId(Integer userId);

    List<Menus> getAllMenuList();

    int getExistByRoleIdMenuId(Integer menuId, Integer roleId);

}
