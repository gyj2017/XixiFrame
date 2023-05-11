package com.gyj.api.common.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.gyj.api.constant.JwtConstant;
import com.gyj.api.domain.SysUser;
import com.gyj.api.service.SysUserService;
import com.gyj.api.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String[] URL_WHITE_LIST = {"/login", "/logout", "/captcha", "/password", "/image/**", "/test/**"};

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MyUserDetailsServiceImp myUserDetailsService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        System.out.println("请求url:" + request.getRequestURI());
        // token为空或者url在白名单里面，则放行
        if (StrUtil.isEmpty(token) || new ArrayList<String>(Arrays.asList(URL_WHITE_LIST)).contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        boolean res = JWTUtil.verify(token, JwtConstant.JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        if (!res) {
            throw new JwtException("token验证失败");
        }

        String  username = JwtUtil.parseToken(token);
        SysUser sysUser = sysUserService.getByUserName(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, myUserDetailsService.getUserAuthority());



    }
}
