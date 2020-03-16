package com.zhangnx.system.controller;

import com.zhangnx.system.pojo.Authority;
import com.zhangnx.system.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/authController")
public class AuthorityController {

   @Autowired
   AuthorityService authService;


    @RequestMapping(value ="/getAuthByName")
    @ResponseBody
    public Map<String,Object> getAuthByName(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("authName",request.getParameter("authName"));
        paramMap.put("pageStart",request.getParameter("pageStart"));
        paramMap.put("pageSize",request.getParameter("pageSize"));

        int totalCount= authService.getAuthtotalCount();
        List<Authority> authorityList = authService.getAuthByName(paramMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("totalCount",totalCount);
        resultMap.put("authList",authorityList);

        return resultMap;
    }


    @RequestMapping("/addAuthority")
    @ResponseBody
    public String addAuthority(HttpServletRequest request,Authority authority){
        String msg="";
        int count = authService.getAuthorityByName(authority.getAuthName());
        if (count>0){
            msg = "用户名已存在";
        }else {
            try {
                int i = authService.addAuthority(authority);
                if (i>0)
                    msg = "新增成功";
            } catch (Exception e){
                e.printStackTrace();
                msg = "新增失败";
            }
        }
        return msg;
    }


    @RequestMapping(value = "/delAuthority")
    @ResponseBody
    public String delAuthority(HttpServletRequest request, HttpServletResponse response){
        String authId= request.getParameter("authId");
        String msg = "";
        try {
            int num = authService.delAuthority(Integer.parseInt(authId));
            if (num>0)
                msg = "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "删除失败";
        }
        return msg;
    }


    @RequestMapping("/editAuthority")
    @ResponseBody
    public String editAuthority(HttpServletRequest request,Authority authority){
        String res = "";
        try{
            int i = authService.updateUser(authority);
            if (i>0)
                res = "修改成功";
        }catch (Exception e){
            e.printStackTrace();
            res = "修改失败";
        }
        return res;
    }







}