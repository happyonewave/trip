package com.qin.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qin.mapper.BaseMapper;
import com.qin.model.auth.Permission;

/** 
 * @Description 菜单Mapper
 *
 * @date Apr 12, 2017 9:12:27 AM  
 */
@Mapper
public interface PermissionMapper extends BaseMapper<String, Permission> {

    /**
     * 查询用户所能访问的所有菜单
     *
     * @param userId
     *            用户
     * @return permissions 菜单
     */
    public List<Permission> findPermissionByUserId(String userId);

    /**
     * 根据菜单KEY查询菜单
     *
     * @param permissionKey
     *            菜单KEY
     * @return
     */
    public Permission findPermissionByKey(String permissionKey);
}
