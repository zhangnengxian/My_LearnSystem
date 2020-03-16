package com.zhangnx.system.service.impl;

import com.zhangnx.system.dao.UserDao;
import com.zhangnx.system.pojo.Users;
import com.zhangnx.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImlp implements UserService {

    @Autowired
    UserDao userDao;

    public List<Users> getUserList() {
        return userDao.getUserList();
    }

    public Users getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<Users> getUserListBydivId(Map<String,String> paramMap) {
        String userName=paramMap.get("userName");
        String loginName = null;
        String name = null;
        if (!"66".equals(userName)) {
            if (userName.matches("[a-zA-Z]+")) {
                loginName = userName;
            } else {
                name = userName;
            }
        }
            paramMap.put("loginName",loginName);
            paramMap.put("name",name);
        return userDao.getUserListBydivId(paramMap);
    }

    @Override
    public int getUsertotalCount() {
        return userDao.getUsertotalCount();
    }

    @Override
    public int saveUser(Users user) {
        return userDao.saveUser(user);
    }

    @Override
    public int delUser(Integer userId) {
        return userDao.delUser(userId);
    }


    @Override
    public int updateUser(Users user) {
        return userDao.updateUser(user);
    }

    @Override
    public Users getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


}
