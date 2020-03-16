package com.test.sysmanager;

import com.zhangnx.system.dao.UserDao;
import com.zhangnx.system.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations = {"classpath*:spring*.xml"}) //加载配置文件
@WebAppConfiguration
public class TestUser {

    @Resource
    UserDao userDao;

    @Test
    public void getUser(){
        List<Users> usersList = userDao.getUserList();
       //List<Users> usersList = userDao.getUserListBydivId(1,"zhangnx",null);
        for (Users u:usersList) {
            System.out.println(u);
        }
    }


}
