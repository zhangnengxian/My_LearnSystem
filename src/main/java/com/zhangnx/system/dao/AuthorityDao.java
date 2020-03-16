package com.zhangnx.system.dao;

import com.zhangnx.system.pojo.Authority;

import java.util.List;
import java.util.Map;

public interface AuthorityDao {

    List<String> getAuthorityListByUserName(Integer userId);

    List<Authority> getAuthByName(Map<String,String> paramMap);

    int getAuthtotalCount();

    int getAuthorityByName(String authName);

    int addAuthority(Authority authority);

    int editAuthority(Authority authority);

    int delAuthority(Integer authId);

    List<Authority> getexitAuthByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

    List<Authority> getoptionalAuthByRoleId(Integer roleId, Integer pageStart, Integer pageSize);

    int addAuthorityToRole(Integer roleId, Integer authId);

    int removeAuthorityToRole(Integer roleId, Integer authId);

    int getCountOptionalAuth(Integer roleId);

    int getCountExitAuth(Integer roleId);
}
