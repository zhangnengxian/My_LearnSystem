package com.zhangnx.system.service;

import com.zhangnx.system.pojo.Authority;
import com.zhangnx.system.pojo.Roles;
import com.zhangnx.system.pojo.Users;

import java.util.List;
import java.util.Map;

public interface RoleService {
  public List<Roles> getRoleList(Map<String,String> paramMap);

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

    List<Users> getExitUserByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

  List<Users> getOptionalUserByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

  List<Authority> getExitAuthByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

  List<Authority> getOptionalAuthByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

    int addAuthorityToRole(Integer roleId, Integer authId);

  int removeAuthorityToRole(Integer roleId, Integer authId);

  int getCountOptionalUser(Integer roleId);

  int getCountExitUser(Integer roleId);

    int getCountOptionalAuth(Integer roleId);

  int getCountExitAuth(Integer roleId);
}
