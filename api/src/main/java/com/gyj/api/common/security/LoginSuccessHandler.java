package com.gyj.api.common.security;

import cn.hutool.json.JSONUtil;
import com.gyj.api.domain.AjaxResult;
import com.gyj.api.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String username = authentication.getName();
        String token = JwtUtil.generateToken(username);
        HashMap<String, String> map = new HashMap<>();
        map.put("authorization", token);

        AjaxResult result = AjaxResult.success("登录成功", map);
        outputStream.write(JSONUtil.toJsonStr(result).getBytes());
        outputStream.flush();
        outputStream.close();

    }

}
