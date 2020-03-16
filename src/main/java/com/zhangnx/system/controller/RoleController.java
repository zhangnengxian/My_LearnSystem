package com.zhangnx.system.controller;

import com.zhangnx.system.pojo.*;
import com.zhangnx.system.service.MenusService;
import com.zhangnx.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/roleController")
public class RoleController extends BaseController {

   @Autowired
   RoleService roleService;
    @Autowired
    MenusService menusService;

    @RequestMapping(value ="/getRole")
    @ResponseBody
    public Map<String,Object> getRole(HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("roleName",request.getParameter("roleName"));
        paramMap.put("pageStart",request.getParameter("pageStart"));
        paramMap.put("pageSize",request.getParameter("pageSize"));

        int totalCount= roleService.getRoletotalCount();
        List<Roles> roleList = roleService.getRoleList(paramMap);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("totalCount",totalCount);
        resultMap.put("roleList",roleList);

        return resultMap;
    }



    @RequestMapping(value ="/saveRole")
    @ResponseBody
    public String save(HttpServletRequest request,Roles role){
        String roleName = request.getParameter("roleName");
        role.setRoleName(roleName);
        String res = "";
        if (roleService.getRoleByName(roleName)!=null){
            res="角色已存在";
        }else {
            try {
                int count = roleService.addRole(role);
                if (count>0)
                    res = "保存成功";
            }catch (Exception e){
                e.printStackTrace();
                res="保存失败";
            }
        }
        return res;
    }


    //删除角色
    @RequestMapping("/delRole")
    @ResponseBody
    public String delRole(int roleId){
        String msg = "";
        try {
            int count = roleService.delRole(roleId);
            if (count>0)
                msg="删除成功";
        }catch (Exception e){
            e.printStackTrace();
            msg="删除失败";
        }
        return  msg;
    }



    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(HttpServletRequest request,Roles role){
        role.setRoleId(Integer.parseInt(request.getParameter("roleId")));
        role.setRoleName(request.getParameter("roleName"));

        String res = "";

        try {
            int count = roleService.updateRole(role);
            if (count>0)
                res = "修改成功";
        }catch (Exception e){
            e.printStackTrace();
            res = "修改失败";
        }
        return res;
    }




    @RequestMapping("/selectedRoleListByUserId")
    @ResponseBody
    public List<Roles> selectedRoleListByUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");
        List<Roles> roleList = roleService.selectedRoleListByUserId(Integer.parseInt(userId));

        return roleList;

    }

    @RequestMapping("/optionalRoleListByUserId")
    @ResponseBody
    public List<Roles> optionalRoleListByUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");
        List<Roles> roleList = roleService.optionalRoleListByUserId(Integer.parseInt(userId));
        return roleList;

    }



    @RequestMapping("/addUserToRole")
    @ResponseBody
    public String addUserToRole(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String roleId = request.getParameter("roleId");
        String msg = "";
        try {
            int count = roleService.addUserToRole(Integer.parseInt(userId), Integer.parseInt(roleId));
            if (count > 0)
                msg = "分配成功";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "分配失败";
        }
        return msg;
    }




    @RequestMapping("/delUserToRole")
    @ResponseBody
    public String delUserToRole(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String roleId = request.getParameter("roleId");
        String msg = "";
        try {
            int count = roleService.delUserToRole(Integer.parseInt(userId), Integer.parseInt(roleId));
            if (count > 0)
                msg = "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "删除失败";
        }
        return msg;
    }



    /**
     * 功能菜单分配，角色已有的功能
     * @param request
     * @return
     */
    @RequestMapping("getExistMenuByRoleId")
    @ResponseBody
    public List<Nodes> getexistMenuByRoleId(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        List<Menus> menusList = menusService.getExistMenuByRoleId(Integer.parseInt(roleId));
       return getMenuListTree(menusList);

    }
    /**
     * 功能菜单分配，角色可选(没有)的功能
     * @param request
     * @return
     */
    @RequestMapping("getOptionalMenuByRoleId")
    @ResponseBody
    public List<Nodes> getOptionalMenuByRoleId(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        List<Menus> menusList = menusService.getOptionalMenuByRoleId(Integer.parseInt(roleId));
        return getMenuListTree(menusList);
    }


    @RequestMapping("/addMenuToRole")
    @ResponseBody
    public String addMenuToRole(HttpServletRequest request) {
        Integer menuId = Integer.parseInt(request.getParameter("menuId"));
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        int resultCount=0;
        List<Integer> childreenIds = getAllMenuChildreenId(menuId);
        if (childreenIds.size()>0) {//保存本节点及子节点
            childreenIds.add(menuId);//添加本节点ID到List中
            for (Integer m : childreenIds) {
                int count = menusService.getExistByRoleIdMenuId(m,roleId);
                if (count>0){
                    continue;
                }
                resultCount=roleService.addMenuToRole(m,roleId);
            }
        }
        List<Integer> parentIdList = getAllMenuParentId(menuId);
        if (parentIdList.size()>0) {//保存父节点
            parentIdList.add(menuId);
            for (Integer m : parentIdList) {
                int count = menusService.getExistByRoleIdMenuId(m,roleId);
                if (count>0){
                    continue;
                }
                resultCount=roleService.addMenuToRole(m,roleId);
            }
        }
        String msg="";
        if (resultCount>0)msg="添加成功";
        else msg="添加失败！";

        return msg;
    }


    @RequestMapping("/delMenuToRole")
    @ResponseBody
    public String delMenuToRole(HttpServletRequest request) {
        Integer menuId = Integer.parseInt(request.getParameter("menuId"));
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));

        int resultCount=0;
        List<Integer> childreenIds = getAllMenuChildreenId(menuId);
                      childreenIds.add(menuId);//添加本节点ID中
        for (Integer m : childreenIds) {
            resultCount=roleService.delMenuToRole(m,roleId);
        }

        String msg = "";
        if (resultCount>0)msg="移除成功";
        else msg="移除失败！";

        return msg;
    }


    @RequestMapping("/getSelectUser")
    @ResponseBody
    public Map<String,Object> getSelectUser(HttpServletRequest request){
        Integer roleId=Integer.parseInt(request.getParameter("roleId"));
        Integer pageStart=Integer.parseInt(request.getParameter("pageStart"));
        Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
        String distinguishParam=request.getParameter("distinguishParam");

        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Users> exitList=null;
        List<Users> optionalList=null;

        if ("exitUser".equals(distinguishParam)){
            exitList=roleService.getExitUserByRoleId(roleId,pageStart,pageSize);
            resultMap.put("exitList",exitList);
            resultMap.put("msg","exitList");
            return resultMap;
        }
        if ("optionalUser".equals(distinguishParam)){
            optionalList = roleService.getOptionalUserByRoleId(roleId,pageStart,pageSize);
            resultMap.put("optionalList",optionalList);
            resultMap.put("msg","optionalList");
            return resultMap;
        }

        int optionalUser_totalRecord = roleService.getCountOptionalUser(roleId);
       resultMap.put("optionalUser_totalRecord",optionalUser_totalRecord);

        int exitUser_totalRecord =  roleService.getCountExitUser(roleId);
        resultMap.put("exitUser_totalRecord",exitUser_totalRecord);

        exitList=roleService.getExitUserByRoleId(roleId,pageStart,pageSize);
        resultMap.put("exitList",exitList);

        optionalList= optionalList = roleService.getOptionalUserByRoleId(roleId,pageStart,pageSize);
        resultMap.put("optionalList",optionalList);

        return resultMap;
    }


    @RequestMapping("/getSelectAuthority")
    @ResponseBody
    public Map<String,Object> getSelectAuthority(HttpServletRequest request) {
        Integer roleId=Integer.parseInt(request.getParameter("roleId"));
        Integer pageStart=Integer.parseInt(request.getParameter("pageStart"));
        Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
        String distinguishParam=request.getParameter("distinguishParam");

        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Authority>  exitList=null;
        List<Authority> optionalList=null;

        if ("exitAuth".equals(distinguishParam)){
            exitList = roleService.getExitAuthByRoleId(roleId,pageStart,pageSize);
            resultMap.put("exitList",exitList);
            resultMap.put("msg","exitList");
            return resultMap;
        }
        if ("optionalAuth".equals(distinguishParam)){
            optionalList = roleService.getOptionalAuthByRoleId(roleId,pageStart,pageSize);
            resultMap.put("optionalList",optionalList);
            resultMap.put("msg","optionalList");
            return resultMap;
        }

        int optionalAuth_totalRecord = roleService.getCountOptionalAuth(roleId);
        resultMap.put("optionalAuth_totalRecord",optionalAuth_totalRecord);

        int exitAuth_totalRecord =  roleService.getCountExitAuth(roleId);
        resultMap.put("exitAuth_totalRecord",exitAuth_totalRecord);

        exitList=roleService.getExitAuthByRoleId(roleId,pageStart,pageSize);
        resultMap.put("exitList",exitList);

        optionalList = roleService.getOptionalAuthByRoleId(roleId,pageStart,pageSize);
        resultMap.put("optionalList",optionalList);

        return resultMap;
    }







    @RequestMapping("/addAuthorityToRole")
    @ResponseBody
    public String addAuthorityToRole(HttpServletRequest request) {
        String msg="";
        Integer roleId=Integer.parseInt(request.getParameter("roleId"));
        Integer authId=Integer.parseInt(request.getParameter("authId"));
        int count = roleService.addAuthorityToRole(roleId,authId);

        if (count>0) msg="添加成功";
        else  msg="添加失败";

        return msg;
    }


    @RequestMapping("/removeaAuthorityToRole")
    @ResponseBody
    public String removeaAuthorityToRole(HttpServletRequest request) {
        String msg="";
        Integer roleId=Integer.parseInt(request.getParameter("roleId"));
        Integer authId=Integer.parseInt(request.getParameter("authId"));
        int count = roleService.removeAuthorityToRole(roleId,authId);

        if (count>0) msg="移除成功";
        else  msg="移除失败";

        return msg;
    }




}