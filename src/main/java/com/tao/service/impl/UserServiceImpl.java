package com.tao.service.impl;

import com.tao.mapper.UserMapper;
import com.tao.model.User;
import com.tao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with suntao on 2018/3/8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
