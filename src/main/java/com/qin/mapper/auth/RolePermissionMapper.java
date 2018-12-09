package com.qin.mapper.auth;

import org.apache.ibatis.annotations.Mapper;

import com.qin.mapper.BaseMapper;
import com.qin.model.auth.RolePermission;

/** 
 * @Description 角色与菜单关系Mapper
 *
 * @date Apr 12, 2017 9:13:04 AM  
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<String, RolePermission> {

    public RolePermission findRolePermission(RolePermission per);

}
