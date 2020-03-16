package com.zhangnx.system.service.impl;

import com.zhangnx.system.dao.AuthorityDao;
import com.zhangnx.system.pojo.Authority;
import com.zhangnx.system.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityDao authorityDao;

    @Override
    public int getAuthtotalCount() {
        return authorityDao.getAuthtotalCount();
    }

    @Override
    public List<Authority> getAuthByName(Map<String, String> paramMap) {
        return authorityDao.getAuthByName(paramMap);
    }

    @Override
    public int getAuthorityByName(String authName) {
        return authorityDao.getAuthorityByName(authName);
    }

    @Override
    public int addAuthority(Authority authority) {
        return authorityDao.addAuthority(authority);
    }

    @Override
    public int updateUser(Authority authority) {
        return authorityDao.editAuthority(authority);
    }

    @Override
    public int delAuthority(Integer authId) {
        return authorityDao.delAuthority(authId);
    }
}
