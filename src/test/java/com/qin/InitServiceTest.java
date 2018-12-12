package com.qin;

import java.util.List;

import com.qin.model.simple.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qin.config.shiro.vo.PermissionEnumUtil;
import com.qin.config.shiro.vo.RoleEnumUtil;
import com.qin.model.auth.Permission;
import com.qin.model.auth.Role;
import com.qin.service.auth.PermissionService;
import com.qin.service.auth.RoleService;
import com.qin.service.auth.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class InitServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 创建角色
     */
    private void addRoles() {
        try {
            roleService.addRole(RoleEnumUtil.超级管理员.getRole());
            roleService.addRole(RoleEnumUtil.普通用户.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建菜单
     */
    private void addPermission() {
        try {
            List<Permission> permis = PermissionEnumUtil.getPermissions();
            for (Permission permission : permis) {
                permissionService.addPermission(permission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建用户
     */
    private void addUsers() {
        try {
            Role adminRole = roleService.findRoleByCode(RoleEnumUtil.超级管理员.getRoleCode());
            Role commonRole = roleService.findRoleByCode(RoleEnumUtil.普通用户.getRoleCode());

            String password = "123456";

            User admin = new User();
            admin.setUsername("admin");
//            admin.setEmail("admin@163.com");
//            admin.setTrueName("管理员");
            admin.setPassword(password);
//            admin.setOrganizeId(adminRole.getId());
            userService.addUser(admin, adminRole);

            User zhangsan = new User();
            zhangsan.setUsername("zhangsan");
//            zhangsan.setTrueName("张三");
//            zhangsan.setEmail("zhangsan@139.com");
            zhangsan.setPassword(password);
//            zhangsan.setOrganizeId(commonRole.getId());
            userService.addUser(zhangsan, commonRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 给角色授权
    private void bindRolePermission() {
        try {
            Role adminRole = roleService.findRoleByCode(RoleEnumUtil.超级管理员.getRoleCode());
            Role commonRole = roleService.findRoleByCode(RoleEnumUtil.普通用户.getRoleCode());

            List<Permission> permis = PermissionEnumUtil.getPermissions();
            for (Permission permission : permis) {
                roleService.addRolePermission(adminRole.getCode(), permission.getKey());
                roleService.addRolePermission(commonRole.getCode(), permission.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInit() {
        try {
            addRoles();
            addPermission();
            addUsers();
            bindRolePermission();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
