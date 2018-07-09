package com.tao.mapper;

import com.tao.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByEmployeeId(String employeeId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}