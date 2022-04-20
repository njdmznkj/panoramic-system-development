package com.nari.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nari.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
