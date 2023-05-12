package com.gyj.api.controller;

import cn.hutool.core.util.ObjectUtil;
import com.gyj.api.domain.AjaxResult;
import com.gyj.api.domain.SysUser;
import com.gyj.api.service.SysUserService;
import com.gyj.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/user/list")
    public AjaxResult userList(@RequestHeader(required = false)String token){

        System.out.println(token);
        if (ObjectUtil.isNotEmpty(token)){
            List<SysUser> userList = sysUserService.list();
            System.out.println(userList);
            return AjaxResult.success(userList);
        }else {
            return AjaxResult.error(401, "没有权限访问");
        }



    }

    @RequestMapping("/login")
    public AjaxResult<String> login(){
        String token = JwtUtil.generateToken("6666");
        return AjaxResult.success("请求成功", token);
    }

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        String key = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        System.out.println(key);
    }

}
