package com.zhangnx.system.service;

import com.zhangnx.system.pojo.Authority;

import java.util.List;
import java.util.Map;

public interface AuthorityService {
    int getAuthtotalCount();

    List<Authority> getAuthByName(Map<String,String> paramMap);

    int getAuthorityByName(String authName);

    int addAuthority(Authority authority);

    int updateUser(Authority authority);

    int delAuthority(Integer authId);
}
