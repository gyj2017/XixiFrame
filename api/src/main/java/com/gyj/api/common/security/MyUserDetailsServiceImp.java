package com.gyj.api.common.security;

import cn.hutool.core.util.ObjectUtil;
import com.gyj.api.common.exception.UserAccountLockException;
import com.gyj.api.domain.SysUser;
import com.gyj.api.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义UserDetails
 */
@Service
public class MyUserDetailsServiceImp implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(username);
        if (ObjectUtil.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        } else if (sysUser.getStatus() == 1) {
            throw new UserAccountLockException("该用户账号被封禁，具体请联系管理员");
        }

        return new User(sysUser.getUsername(), sysUser.getPassword(), getUserAuthority(sysUser.getId()));
    }

    public List<GrantedAuthority> getUserAuthority(Long userId) {
        // 格式 ROLE_admin, system:role:delete
        String authority = sysUserService.getUserAuthorityInfo(userId);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
