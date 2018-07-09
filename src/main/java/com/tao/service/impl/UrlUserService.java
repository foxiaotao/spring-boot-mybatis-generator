package com.tao.service.impl;

/**
 * Created with suntao on 2018/6/28
 */

import com.tao.mapper.RoleMapper;
import com.tao.mapper.UserMapper;
import com.tao.model.Role;
import com.tao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义用户名密码校验实现
 *
 * @author CHANY037 2017-11
 *
 */
@Service
public class UrlUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * employeeId 用户工号，在数据库中保证存储唯一
     */
    @Override
    public UserDetails loadUserByUsername(String employeeId) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        User user = userMapper.selectByEmployeeId(employeeId);

        if(user == null){
            throw new UsernameNotFoundException(employeeId + " do not exist!");
        } else {
            System.out.println(user.getPassword() + " --pw-- ");
            List<Role> roles = roleMapper.findRoleByUserId(user.getUserId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            //写入用户的角色  ***  切记 由于框架原因 角色名称要以 ROLE_ 开头 **** 血泪史 ****
            //源码：org.springframework.security.access.expression.SecurityExpressionRoot hasAnyRole()
            for (Role role : roles) {
                if (role != null && role.getRoleName() != null) {
                    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleCode());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            org.springframework.security.core.userdetails.User uu = new org.springframework.security.core.userdetails.User(employeeId, user.getPassword(), grantedAuthorities);
            return uu;
        }
    }
}
