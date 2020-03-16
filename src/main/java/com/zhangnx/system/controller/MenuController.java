package com.zhangnx.system.controller;

import com.zhangnx.system.pojo.Menus;
import com.zhangnx.system.pojo.Nodes;
import com.zhangnx.system.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value ="/menuController")
public class MenuController extends BaseController{

    @Autowired
    private MenusService menusService;

    @RequestMapping(value ="/getMenuListByUserId")
    @ResponseBody
    public List<Nodes> getMenuListByUserId(HttpServletRequest request,HttpSession session){
       List<Menus> menusList = menusService.getMenuListByUserId(UserInfo.getUserId());
            return getMenuListTree(menusList);
    }


    @RequestMapping(value ="/getUserInfo")
    @ResponseBody
    public String getUserInfo(){
        return UserInfo.getUserName();
    }


    @RequestMapping(value ="/getAllMenuList")
    @ResponseBody
    public List<Nodes> getAllMenuList(){
       List<Menus> menusList = menusService.getAllMenuList();
       return getMenuListTree(menusList);
    }



    //添加
    @RequestMapping("addMenus")
    @ResponseBody
    public String  addMenus(HttpServletRequest request, Menus menu){
        menu.setParentId(Integer.parseInt(request.getParameter("parentMenus")));
        menu.setMenuName(request.getParameter("menuName"));
        menu.setIconCls(request.getParameter("menuIconCls"));
        menu.setUrl(request.getParameter("menuUrl"));
        menu.setEnable(request.getParameter("isFlay"));
        menu.setSort(Integer.parseInt(request.getParameter("menuSort")));
        menusService.addMenus (menu);
        return "添加成功！";
    }

    //修改
    @RequestMapping("editMenus")
    @ResponseBody
    public String editMenus(HttpServletRequest request,Menus menu){
        menu.setParentId(Integer.parseInt(request.getParameter("parentMenus")));
        menu.setMenuName(request.getParameter("menuName"));
        menu.setIconCls(request.getParameter("menuIconCls"));
        menu.setUrl(request.getParameter("menuUrl"));
        menu.setEnable(request.getParameter("isFlay"));
        menu.setSort(Integer.parseInt(request.getParameter("menuSort")));
        menusService.editMenus (menu);

        return "修改成功！";
    }

    //删除
    @RequestMapping("delMenus")
    @ResponseBody
    public String delMenus(HttpServletRequest request){
        String menuId = request.getParameter("menuId");
        menusService.delMenus(Integer.parseInt(menuId));

        return "删除成功！";
    }




    @RequestMapping(value ="/unPermissionPage")
    public String unPermissionPage(){
        return "sysmanager/unPermissionPage";
    }

    @RequestMapping(value ="/homePage")
    public String homePage(){
        return "sysmanager/homePage";
    }


    @RequestMapping(value ="/authorityPage")
    public String userPage(){
        return "sysmanager/authorityPage";
    }

    @RequestMapping(value ="/menuPage")
    public String menuPage(){
        return "sysmanager/menuPage";
    }



}