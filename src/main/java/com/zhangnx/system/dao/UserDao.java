package com.zhangnx.system.dao;

import com.zhangnx.system.pojo.Users;

import java.util.List;
import java.util.Map;

//@Repository("UserDao")
public interface UserDao {

    public List<Users> getUserList();

    public  Users getUserByName(String loginName);

    public  List<Users> getUserListBydivId(Integer divId,String loginName,String name);

    public  List<Users> getUserListBydivId(Map paramMap);

    public  int saveUser(Users user);

    public  int delUser(Integer userId);

    public  int updateUser(Users user);

    public  Users getUserById(Integer userId);

    public  int getUsertotalCount();

    List<Users> getExitUserByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

    List<Users> getOptionalUserByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

    int getCountExitUser(Integer roleId);

    int getCountOptionalUser(Integer roleId);
}
