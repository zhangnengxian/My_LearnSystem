package com.zhangnx.system.service.impl;

import com.zhangnx.system.dao.AuthorityDao;
import com.zhangnx.system.dao.RoleDao;
import com.zhangnx.system.dao.UserDao;
import com.zhangnx.system.pojo.Authority;
import com.zhangnx.system.pojo.Roles;
import com.zhangnx.system.pojo.Users;
import com.zhangnx.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImlp implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    AuthorityDao authorityDao;
    @Override
    public List<Roles> getRoleList(Map<String,String> paramMap) {
        String roleName = paramMap.get("roleName");
        String roleName66 = null;
        if (roleName!=null && !"".equals(roleName)) {
            roleName66 =roleName;
        }
        paramMap.remove("roleName");
        paramMap.put("roleName",roleName66);
        return roleDao.getRoleList(paramMap);
    }

    @Override
    public int getRoletotalCount() {
        return roleDao.getRoletotalCount();
    }

    @Override
    public int getCountExitUser(Integer roleId) {
        return userDao.getCountExitUser(roleId);
    }


    @Override
    public List<Users> getExitUserByRoleId(Integer roleId, Integer pageStart, Integer pageSize) {
        return userDao.getExitUserByRoleId(roleId,pageStart,pageSize);
    }

    @Override
    public int getCountOptionalUser(Integer roleId) {
        return userDao.getCountOptionalUser(roleId);
    }

    @Override
    public List<Users> getOptionalUserByRoleId(Integer roleId, Integer pageStart, Integer pageSize) {
        return userDao.getOptionalUserByRoleId(roleId,pageStart,pageSize);
    }





    @Override
    public int getCountExitAuth(Integer roleId) {
        return authorityDao.getCountExitAuth(roleId);
    }

    @Override
    public List<Authority> getExitAuthByRoleId(Integer roleId, Integer pageStart, Integer pageSize) {
        return authorityDao.getexitAuthByRoleId(roleId,pageStart,pageSize);
    }

    @Override
    public int getCountOptionalAuth(Integer roleId) {
        return authorityDao.getCountOptionalAuth(roleId);
    }

    @Override
    public List<Authority> getOptionalAuthByRoleId(Integer roleId, Integer pageStart, Integer pageSize) {
        return authorityDao.getoptionalAuthByRoleId(roleId,pageStart,pageSize);
    }




    @Override
    public int addAuthorityToRole(Integer roleId, Integer authId) {
        return authorityDao.addAuthorityToRole(roleId,authId);
    }

    @Override
    public int removeAuthorityToRole(Integer roleId, Integer authId) {
        return authorityDao.removeAuthorityToRole(roleId,authId);
    }


    @Override
    public Roles getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    public int addRole(Roles role) {
        return roleDao.addRole(role);
    }

    @Override
    public int delRole(Integer roleId) {
        return roleDao.delRole(roleId);
    }

    @Override
    public int updateRole(Roles role) {
        return roleDao.updateRole(role);
    }

    @Override
    public List<Roles> selectedRoleListByUserId(Integer userId) {
        return roleDao.selectedRoleListByUserId(userId);
    }

    @Override
    public List<Roles> optionalRoleListByUserId(Integer userId) {
        return roleDao.optionalRoleListByUserId(userId);
    }


    @Override
    public int addUserToRole(Integer userId, Integer roleId) {
        return roleDao.addUserToRole(userId,roleId);
    }

    @Override
    public int delUserToRole(Integer userId, Integer roleId) {
        return roleDao.delUserToRole(userId,roleId);
    }

    @Override
    public int addMenuToRole(Integer menuId, Integer roleId) {
        return roleDao.addMenuToRole(menuId,roleId);
    }

    @Override
    public int delMenuToRole(Integer menuId, Integer roleId) {
        return roleDao.delMenuToRole(menuId,roleId);
    }



}
