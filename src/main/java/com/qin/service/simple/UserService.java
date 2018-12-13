package com.qin.service.simple;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.User;
import com.qin.model.simple.UserExample;

import java.util.List;

public interface UserService {

    public boolean addUser(User user);

    public boolean editUser(User user);

    public User findUserById(Integer userId);

    List<User> selectByExample(UserExample example);

    List<User> select();

    public List<User> findUserByKeywords(String keywords);

    public PageInfo<User> findUserByPage(Integer pageNum, String keywords);

    public PageInfo<User> findUserByPage1(Integer pageNum, String keywords);

    public PageInfo<User> findUserByPage2(Integer pageNum, String keywords);

}