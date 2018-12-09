package com.qin.mapper.auth;

import com.qin.mapper.BaseMapper;
import com.qin.model.auth.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * @Description 角色Mapper
 *
 * @date Apr 12, 2017 9:12:42 AM  
 */
@Mapper
public interface RoleMapper extends BaseMapper<String, Role> {

    /**
     * 根据用户查询对应所有角色
     *
     * @param userId
     *            用户
     * @return roles 所有角色
     */
    public List<Role> findRoleByUserId(String userId);

    /**
     * 根据编码查询角色
     *
     * @param code
     *            角色编码
     * @return
     */
    public Role findRoleByCode(String code);

}
