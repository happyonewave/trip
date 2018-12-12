package com.qin.service.auth.impl;

// import com.qin.mapper.auth.RoleMapper;
//import com.qin.mapper.auth.UserMapper;

import com.qin.mapper.simple.UserMapper;
import com.qin.model.auth.Role;
//import com.qin.model.auth.User;
import com.qin.model.simple.User;
import com.qin.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private RoleMapper roleMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public Role findRoleByRoleCode(String roleCode) {
//        return roleMapper.findRoleByCode(roleCode);
        return null;
    }

    @Override
    public List<User> findUserByRoleCode(String roleCode) {
//        return userMapper.selectUserByRoleCode(roleCode);
        return null;
    }

}
