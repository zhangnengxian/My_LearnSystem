package security;

import com.zhangnx.system.dao.AuthorityDao;
import com.zhangnx.system.dao.UserDao;
import com.zhangnx.system.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthorityDao authorityDao;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        //查询用户信息
        Users user = userDao.getUserByName(userName);
        Collection<GrantedAuthority> grantedAuths =null;
        UserDetails userdetails =null;

        if (user!=null) {
            grantedAuths = obtionGrantedAuthorities(user);
            userdetails = new UserDetails(user.getLoginName(), user.getPassWord(), true, true, true, true, grantedAuths);
        }
         userdetails.setUserId(user.getUserId());
         userdetails.setName(user.getName());
         userdetails.setDivId(user.getDivId());
        return userdetails;

    }
    private Set<GrantedAuthority> obtionGrantedAuthorities(Users user) {
        //根据用户名查询角色具有的权限
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        List<String> strList = authorityDao.getAuthorityListByUserName(user.getUserId());
        if (strList!=null){
            for (String str : strList) {
                authSet.add(new SimpleGrantedAuthority(str));
            }
        }
        return authSet;
    }

}