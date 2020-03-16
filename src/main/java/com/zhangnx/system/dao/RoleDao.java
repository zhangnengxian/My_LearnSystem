package com.zhangnx.system.dao;

import com.zhangnx.system.pojo.Roles;

import java.util.List;
import java.util.Map;


public interface RoleDao {

    public List<Roles> getRoleList(Map roleName);

    public Roles getRoleByName(String roleName);

    public int addRole(Roles role);

    public int delRole(Integer roleId);

    public int updateRole(Roles role);

    public List<Roles> selectedRoleListByUserId(Integer userId);

    public List<Roles> optionalRoleListByUserId(Integer userId);

    public int addUserToRole(Integer userId, Integer roleId);

    public int delUserToRole(Integer userId, Integer roleId);

    public int addMenuToRole(Integer menuId, Integer roleId);

    public int delMenuToRole(Integer menuId, Integer roleId);

    public int getRoletotalCount();

}
