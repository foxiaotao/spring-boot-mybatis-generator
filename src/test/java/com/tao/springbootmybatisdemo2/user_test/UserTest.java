package com.tao.springbootmybatisdemo2.user_test;

import com.tao.mapper.UserMapper;
import com.tao.model.User;
import com.tao.springbootmybatisdemo2.SpringbootMybatisDemo2ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with suntao on 2018/3/8
 */
public class UserTest extends SpringbootMybatisDemo2ApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void userTest(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println("user-password:"+user.getPassword());
    }
}
