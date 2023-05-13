package com.gyj.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.api.domain.SysMenu;
import com.gyj.api.mapper.SysMenuMapper;
import com.gyj.api.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xixihaha
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2023-05-12 21:16:08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    @Override
    public List<SysMenu> buildTreeMenu(List<SysMenu> menuList) {
        List<SysMenu> resMenuList = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            for (SysMenu item : menuList) {
                if (item.getParentId().equals(sysMenu.getId())) {
                    sysMenu.getChildren().add(item);
                }
            }
            if (sysMenu.getParentId() == 0L) {
                resMenuList.add(sysMenu);
            }
        }
        return resMenuList;
    }
}




