package utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import security.UserDetails;

import java.util.Collection;

/**
 * @author yongyang
 * @create 2018-03-24
 * @desc 时间操作类
 */
public class UserInfo {

    public  static UserDetails getUser() {
        UserDetails userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
             userDetails = (UserDetails) authentication.getPrincipal();
        }
        return userDetails;
    }




    public  static String getUserName() {
        UserDetails userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        return userDetails.getName();
    }





    public  static String getLoginName() {
        UserDetails userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        return userDetails.getLoginName();
    }




    public  static Integer getUserId() {
        UserDetails userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        return userDetails.getUserId();
    }



    public  static Collection<GrantedAuthority> getUserAuthority() {
        UserDetails userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        return userDetails.getAuthorities();
    }







}