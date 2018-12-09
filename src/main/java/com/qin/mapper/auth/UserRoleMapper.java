package com.qin.mapper.auth;

import com.qin.mapper.BaseMapper;
import com.qin.model.auth.UserRole;
import org.apache.ibatis.annotations.Mapper;

/** 
 * @Description 用户与角色关系Mapper
 *
 * @date Apr 12, 2017 9:13:44 AM  
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<String, UserRole> {

}
