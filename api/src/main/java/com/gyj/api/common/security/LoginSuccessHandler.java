package com.gyj.api.common.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyj.api.domain.AjaxResult;
import com.gyj.api.domain.SysMenu;
import com.gyj.api.domain.SysRole;
import com.gyj.api.domain.SysUser;
import com.gyj.api.service.SysMenuService;
import com.gyj.api.service.SysRoleService;
import com.gyj.api.service.SysUserService;
import com.gyj.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String username = authentication.getName();
        String token = JwtUtil.generateToken(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("authorization", token);

        SysUser currentUser = userService.getByUserName(username);

        // 获取角色信息
        List<SysRole> roleList = roleService.list(new QueryWrapper<SysRole>().inSql("id", "SELECT role_id FROM sys_user_role WHERE user_id = " + currentUser.getId()));

        // 遍历角色获取所有不重复菜单权限
        Set<SysMenu> menuSet = new HashSet<>();
        for (SysRole role : roleList) {
            List<SysMenu> menuList = menuService.list(new QueryWrapper<SysMenu>().inSql("id", "SELECT menu_id FROM sys_role_menu WHERE role_id = " + role.getId()));
            if (menuList.size() == 0){
                continue;
            }
            for (SysMenu menu : menuList){
                menuSet.add(menu);
            }
        }

        List<SysMenu> menuList = new ArrayList<>(menuSet);
        // 排序
        menuList.sort(Comparator.comparing(SysMenu::getOrderNum));

        // 转菜单树
        List<SysMenu> treeMenuList = menuService.buildTreeMenu(menuList);
        map.put("menuList", treeMenuList);
        AjaxResult result = AjaxResult.success("登录成功", map);
        outputStream.write(JSONUtil.toJsonStr(result).getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
