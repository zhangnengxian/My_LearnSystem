package com.zhangnx.system.service;
import com.zhangnx.system.pojo.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
  List<Users> getUserList();

  Users getUserByName(String username);

  int saveUser(Users user);

  int delUser(Integer i);

  int updateUser(Users user);

  Users getUserById(Integer userId);

  int getUsertotalCount();

  List<Users> getUserListBydivId(Map<String,String> paramMap);
}
