package com.gyj.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.api.domain.SysUser;
import com.gyj.api.service.SysUserService;
import com.gyj.api.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author gaoyijie
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-05-10 14:25:29
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}



