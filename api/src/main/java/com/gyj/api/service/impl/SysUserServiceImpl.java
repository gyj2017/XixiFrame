package com.gyj.api.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.api.domain.SysMenu;
import com.gyj.api.domain.SysRole;
import com.gyj.api.domain.SysUser;
import com.gyj.api.mapper.SysMenuMapper;
import com.gyj.api.mapper.SysRoleMapper;
import com.gyj.api.service.SysUserService;
import com.gyj.api.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gaoyijie
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-05-10 14:25:29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public SysUser getByUserName(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {
        StringBuffer authority = new StringBuffer();

        // 获取角色信息
        List<SysRole> roleList = sysRoleMapper.selectList(new QueryWrapper<SysRole>().inSql("id", "SELECT role_id FROM sys_user_role WHERE user_id = " + userId));
        if (roleList.size() > 0) {
            String roleStr = roleList.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
            authority.append(roleStr);
        }

        // 遍历角色获取所有不重复菜单权限
        Set<String> menuCodeSet = new HashSet<>();
        for (SysRole role : roleList) {
            List<SysMenu> menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().inSql("id", "SELECT menu_id FROM sys_role_menu WHERE role_id = " + role.getId()));
            if (menuList.size() == 0){
                continue;
            }
            for (SysMenu menu : menuList){
                if (ObjectUtil.isNotEmpty(menu.getPerms())){
                    menuCodeSet.add(menu.getPerms());
                }
            }
        }

        if (menuCodeSet.size() > 0) {
            authority.append(",").append(menuCodeSet.stream().collect(Collectors.joining(",")));
        }
        System.out.println(authority);
        return authority.toString();
    }
}




