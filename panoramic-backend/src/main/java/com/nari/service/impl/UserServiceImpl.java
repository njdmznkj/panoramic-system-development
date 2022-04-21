package com.nari.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nari.dao.UserMapper;
import com.nari.model.User;
import com.nari.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
