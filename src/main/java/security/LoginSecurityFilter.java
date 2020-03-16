package security;


import com.zhangnx.system.dao.UserDao;
import com.zhangnx.system.pojo.Users;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import utils.MD5Utils;
import utils.UserCookieUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginSecurityFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Autowired
    UserDao userDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取前台登录输入的信息
        String username = request.getParameter("username"); //用户名
        String password = request.getParameter("password"); //密码
        String inputCode = request.getParameter("inputCode"); //验证码
        String remember_me = request.getParameter("remember_me"); //验证码
        if (request.getRequestURI().contains("/login_check")){
            String sessionCode = request.getSession().getAttribute("code").toString();//session中的验证码
            try{
                Users user=null;
                try {
                    user=userDao.getUserByName(username);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //将输入的密码加密
                String md5_Password = MD5Utils.md5(password);

                if (!StringUtils.isEmpty(inputCode.trim()) && !inputCode.trim().toLowerCase().equals(sessionCode.trim().toLowerCase())){
                    throw  new MyException("验证码错误");
                }else {
                    if (user==null){
                        throw new MyException("用户名不存在");
                    }else if (user != null && user.getEnable()==1) { //1:用户可用状态，0：用户不可用状态
                        if (!md5_Password.equals(user.getPassWord())) {
                            throw new MyException("密码错误");
                        }
                    }else if (user != null && user.getEnable()==0) {
                        throw new MyException("用户未启用");
                    }
                    //将用户信息存入session中
                    //SaveSession(user,request);
                    //添加Cookie
                    if ("true".equals(remember_me)) { //"true"表示用户勾选记住密码
                        UserCookieUtils.ClearCookie(response,request);
                        UserCookieUtils.AddCookie( username,password, response);
                    }else if ("false".equals(remember_me)){ //删除cookie
                        UserCookieUtils.ClearCookie(response,request);
                    }
                }
            }catch (AuthenticationException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                e.printStackTrace();
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

/*    public void SaveSession(Users user,HttpServletRequest request){
        HttpSession session = request.getSession();
        user.setPassWord(null);
        user.setEmail(null);
        user.setIdCard(null);
        user.setSex(null);
        user.setQq(null);
        user.setRegDate(null);
        user.setEnable(null);
        session.setAttribute("user",user);
    }*/



}














