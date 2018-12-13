package com.qin.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.constants.Constants;
import com.qin.common.exception.BusinessException;
import com.qin.common.util.salt.Digests;
import com.qin.common.util.salt.Encodes;
import com.qin.config.datasource.DataSourceEnum;
import com.qin.config.datasource.TargetDataSource;
import com.qin.config.pk.FactoryAboutKey;
import com.qin.config.pk.TableEnum;
// import com.qin.mapper.auth.RoleMapper;
// import com.qin.mapper.auth.UserRoleMapper;
import com.qin.mapper.simple.UserMapper;
import com.qin.model.auth.Role;
import com.qin.model.auth.UserRole;
import com.qin.model.simple.User;
import com.qin.model.simple.UserExample;
import com.qin.service.auth.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

//    @Autowired
//    private RoleMapper roleMapper;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;


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
        UserExample.Criteria criteria = example.createCriteria();
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
        example.setOrderByClause("create_time desc");
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andSaltIsNull();
        if (keywords!=null&&!StringUtils.isBlank(keywords)){
            criteria.andNicknameLike("%" + keywords + "%");
        }
        List<User> user = userMapper.selectByExample(example);
//        List<User> user = userMapper.select();
        // 用PageInfo对结果进行包装
        PageInfo<User> page = new PageInfo<User>(user);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }


    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    @Transactional
    @Override
    public void addUser(User user, Role role) {
//        if (user == null || role == null) {
//            throw new BusinessException("user.registr.error", "注册信息错误");
//        }
//
//        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
//            throw new BusinessException("user.registr.error", "注册信息错误");
//        }
//
//        if (StringUtils.isBlank(role.getId())) {
//            throw new BusinessException("user.registr.error", "用户未指定所属角色");
//        }
//
//        // Role r = daoService.getByPrimaryKey(Role.class, role.getId());
//        Role r = roleMapper.findById(role.getId());
//        if (r == null) {
//            throw new BusinessException("user.registr.error", "用户未指定所属组织或角色");
//        }
//
//        User u = userMapper.selectByUserName(user.getUsername());
//        if(u!=null){
//            throw new BusinessException("user.registr.error", "用户账号已经存在,username="+user.getUsername());
//        }
//
//        entryptPassword(user);
////        user.setStatus(Constants.STATUS_VALID);
//        user.setCreateTime(Calendar.getInstance().getTime());
//        user.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_USER));
//        userMapper.insert(user);
//
//        UserRole ur = new UserRole();
//        ur.setRoleId(r.getId());
//        ur.setUserId(user.getId());
//        ur.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_USER_ROLE));
//        // daoService.save(ur);
//        userRoleMapper.insert(ur);
    }

    @Override
    public void updatePassword(User user) {
//        if (log.isDebugEnabled()) {
//            log.debug("## update User password.");
//        }
//        User u = userMapper.selectByPrimaryKey(user.getId());
//        u.setPassword(user.getPassword());
//        entryptPassword(u);
//        u.setModifyTime(Calendar.getInstance().getTime());
//        // daoService.update(u);
//        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public User findUserByName(String username) {
        try {
            return userMapper.selectByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("# 根据账号查询用户报错 , username={}", username);
            throw new BusinessException("1001", "查询用户失败");
        }
    }

    @Transactional
    @Override
    public void updateUserLastLoginTime(User user) {
        User u = userMapper.selectByPrimaryKey(user.getId());
        if (u != null) {
            user = new User();
//            user.setLastLoginTime(Calendar.getInstance().getTime());
            user.setId(u.getId());
            userMapper.updateByPrimaryKeySelective(u);
        }
    }

    @Override
    public List<User> findUsers() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public List<User> findEmp(String shopId, String empName) {
//        return userMapper.findEmp(Constants.COMMON_ROLE_CODE, Constants.STATUS_VALID, shopId, empName);
        return null;
    }


}
