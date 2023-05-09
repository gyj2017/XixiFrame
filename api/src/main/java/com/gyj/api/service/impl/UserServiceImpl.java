package com.gyj.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.api.domain.User;
import com.gyj.api.service.UserService;
import com.gyj.api.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xixihaha
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-05-09 21:36:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




