package com.qin.mapper.auth;

import com.qin.mapper.BaseMapper;
import com.qin.model.auth.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @Description 用户Mapper
 *
 * @date Apr 12, 2017 9:13:21 AM  
 */
//@Mapper
public interface UserMapper extends BaseMapper<String, User> {


    /**
     * 根据用户名查询用户
     *
     * @param username
     *            用户名
     * @return user 用户
     */
    public User findUserByName(String username);

    /**
     * 查询店铺所有用户
     *
     * @param id
     *            店铺ID
     * @return
     * @author zhangsan
     */
    public List<User> findUserByShop(String organizeId);

    /**
     * 查询组织下所有客服员工
     *
     * @return
     */
    public List<User> findUsers();

    /**
     * 根据条件（店铺、名称）查询客服人员
     *
     * @param shopId
     *            店铺ID
     * @param empName
     *            客服人员名称
     * @return
     */
    public List<User> findEmp(String roleCode, Integer status, String shopId, String empName);

     /**
     * 根据用户名查询用户
     *
     * @param username
     *            用户名
     * @return user 用户
     */
    public List<User> findUserByRoleCode(@Param("roleCode") String username);

}
