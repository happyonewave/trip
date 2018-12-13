package com.qin.service.simple.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.constants.Constants;
import com.qin.config.datasource.DataSourceEnum;
import com.qin.config.datasource.TargetDataSource;
import com.qin.mapper.simple.UserMapper;
import com.qin.model.simple.User;
import com.qin.model.simple.UserExample;
import com.qin.model.simple.UserExample.Criteria;
import com.qin.service.simple.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @Description 用户接口实现类
 */
//@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public boolean addUser(User user) {
        if (user != null) {
//            user.setId(Integer.parseInt(FactoryAboutKey.getPK(TableEnum.PRODUCT)) );
            user.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            int flag = userMapper.insert(user);
            // if (StringUtils.equals(user.getTitle(), "a"))
            // throw new BusinessException("001", "测试事务回溯");
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public boolean editUser(User user) {
        if (user != null && StringUtils.isNotBlank(user.getId() + "")) {
            int flag = userMapper.updateByPrimaryKeySelective(user);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public User findUserById(Integer id) {
        if (StringUtils.isBlank(id + ""))
            return null;
        else
            return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectByExample(UserExample example) {
        example = new UserExample();
        Criteria criteria = example.createCriteria();
//        criteria.andStockEqualTo(99);
        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> select() {
        return userMapper.selectByExample(new UserExample());
    }

    // 默认数据库
    @Override
    public List<User> findUserByKeywords(String keywords) {
        log.info("# 查询默认数据库");
        return userMapper.findUserByKeywords(keywords);
    }

    // 数据库1
    @TargetDataSource(DataSourceEnum.DB1)
    @Override
    public PageInfo<User> findUserByPage1(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<User> user = userMapper.findUserByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<User> page = new PageInfo<User>(user);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库1 page.toString()={}", page.toString());
        return page;
    }

    // 数据库2
    @TargetDataSource(DataSourceEnum.DB2)
    @Override
    public PageInfo<User> findUserByPage2(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<User> user = userMapper.findUserByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<User> page = new PageInfo<User>(user);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库2 page.toString()={}", page.toString());
        return page;
    }

    @Override
    public PageInfo<User> findUserByPage(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
//        List<User> user = userMapper.findUserByPage(keywords);
        UserExample example = new UserExample();
        example.setOrderByClause("time desc");
        List<User> user = userMapper.selectByExample(example);
//        List<User> user = userMapper.select();
        // 用PageInfo对结果进行包装
        PageInfo<User> page = new PageInfo<User>(user);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }

}
