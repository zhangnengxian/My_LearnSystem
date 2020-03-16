package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCookieUtils {

    //添加cookie
    public static void AddCookie(String username, String password, HttpServletResponse response){
        Cookie cookie = new Cookie(username, password);
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    //清空cookie
    public static void ClearCookie(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            cookie.setValue(null);
            cookie.setMaxAge(0);// 立即销毁cookie
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}
