package com.qin.service.auth.impl;

import com.qin.common.exception.BusinessException;
import com.qin.config.pk.FactoryAboutKey;
import com.qin.config.pk.TableEnum;
import com.qin.mapper.auth.PermissionMapper;
// import com.qin.mapper.auth.RoleMapper;
// import com.qin.mapper.auth.RolePermissionMapper;
import com.qin.model.auth.Permission;
import com.qin.model.auth.Role;
import com.qin.model.auth.RolePermission;
import com.qin.service.auth.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    //// TODO: 18-12-7 返回的Role都固定,靜态
    private final static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

//    @Autowired
//    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

//    @Autowired
//    private RolePermissionMapper rolePermissionMapper;

    @Transactional
    public void addRole(Role role) {
        if (role == null || StringUtils.isBlank(role.getName())) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("## 添加角色 : {}", role.getName());
        }
        Role r = findRoleByCode(role.getCode());
        if (r == null) {
            role.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_ROLE));
//            roleMapper.insert(role);
        }
    }

    @Override
    public Role findRoleByCode(String code) {
        if (log.isDebugEnabled()) {
            log.debug("## 根据编码查询角色 :　{}", code);
        }
//        return roleMapper.findRoleByCode(code);
        return null;
    }

    @Override
    public List<Role> findRoleByUserId(String userId) {
//        return roleMapper.findRoleByUserId(userId);
        return null;
    }

    @Transactional
    @Override
    public void addRolePermission(String roleCode, String permissionKey) {
        Role role = findRoleByCode(roleCode);
        if (role == null) {
            throw new BusinessException("role-fail", "## 给角色授权失败， 角色编码错误");
        }
        Permission permis = permissionMapper.findPermissionByKey(permissionKey);
        if (permis == null) {
            throw new BusinessException("role-fail", "## 给角色授权失败， 菜单KEY不存在，key=" + permissionKey);
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(role.getId());
        rolePermission.setPermissionId(permis.getId());

//        RolePermission rp = rolePermissionMapper.findRolePermission(rolePermission);
//        if (rp == null) {
//            rolePermission.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_ROLE_PERMISSION));
//            rolePermissionMapper.insert(rolePermission);
//        }

    }
}
