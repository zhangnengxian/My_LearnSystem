package com.zhangnx.system.controller;

import com.zhangnx.system.pojo.Users;
import com.zhangnx.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.CodeUtils;
import utils.DateUtils;
import utils.MD5Utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/userController")
public class UserController {

   @Autowired
   UserService userService;





    @RequestMapping(value ="/getUser")
    @ResponseBody
    public List<Users> getUser(HttpServletRequest request){
    List<Users> usersList = userService.getUserList();
    return  usersList;
}

    @RequestMapping(value ="/getUserListBydivId")
    @ResponseBody
    public Map<String,Object> getUserListBydivId(HttpServletRequest request) {

        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("divId",request.getParameter("divId"));
        paramMap.put("userName",request.getParameter("userName"));
        paramMap.put("pageStart",request.getParameter("pageStart"));
        paramMap.put("pageSize",request.getParameter("pageSize"));


        int totalCount= userService.getUsertotalCount();
        List<Users> usersList = userService.getUserListBydivId(paramMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("totalCount",totalCount);
        resultMap.put("userList",usersList);

        return resultMap;


    }



    @RequestMapping("/saveUser")
    @ResponseBody
    public String saveUser(HttpServletRequest request,Users user){
        user.setRegDate(DateUtils.getFormatedDateTime());
        user.setPassWord(MD5Utils.md5(user.getPassWord()));
        String msg = "";

        Users user66 = userService.getUserByName(user.getLoginName());
        if (user66!=null){
            msg = "用户名已存在";
        }else {
            try {
                int i = userService.saveUser(user);
                if (i>0)
                    msg = "注册成功";
            } catch (Exception e){
                e.printStackTrace();
                msg = "注册失败";
            }
        }
        return msg;
    }


    @RequestMapping(value = "/delUser")
    @ResponseBody
    public String delUser(HttpServletRequest request, HttpServletResponse response){
        String userId= request.getParameter("userId");
        String msg = "";
        try {
            int num = userService.delUser(Integer.parseInt(userId));
            if (num>0)
                msg = "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "删除失败";
        }
        return msg;
    }


    @RequestMapping("/updateUser")
    @ResponseBody
    public String update(HttpServletRequest request,Users users){
        Integer userId=users.getUserId();
        String passWord=users.getPassWord();
        users.setRegDate(DateUtils.getFormatedDateTime());
        Users userPsd=userService.getUserById(userId);
        //如果前台传递的密码与数据库中的密码一样不加密，否者加密
        if (!passWord.equals(userPsd.getPassWord())){
            users.setPassWord(MD5Utils.md5(passWord));
        }

        String res = "";
        try{
            int i = userService.updateUser(users);
            if (i>0)
                res = "修改成功";
        }catch (Exception e){
            e.printStackTrace();
            res = "修改失败";
        }
        return res;
    }



    //生成验证码
    @RequestMapping(value = "/verificationCode")
    public  void verificationCode(HttpServletRequest request, HttpServletResponse response){
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtils.generateCodeAndPic();

        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("code", codeMap.get("code").toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value ="/toIndex")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value ="/loginPage")
    public String loginPage(HttpServletRequest request){
        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        //return "redirect:"+basePath+"/WEB-INF/login.html";//重定向
        return  "login";
    }

}