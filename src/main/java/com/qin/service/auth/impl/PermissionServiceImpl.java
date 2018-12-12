package com.qin.service.auth.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.qin.common.exception.BusinessException;
import com.qin.config.pk.FactoryAboutKey;
import com.qin.config.pk.TableEnum;
import com.qin.config.shiro.vo.PermissionVo;
//import com.qin.mapper.auth.PermissionMapper;
import com.qin.model.auth.Permission;
import com.qin.service.auth.PermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

//    @Autowired
//    private PermissionMapper permissionMapper;

    private PermissionVo convertToVo(Permission per) {
        PermissionVo pvo = new PermissionVo();
        pvo.setId(per.getId());
        pvo.setName(per.getName());
        pvo.setCssClass(per.getCssClass());
        pvo.setUrl(per.getUrl());
        pvo.setKey(per.getKey());
        pvo.setParentKey(per.getParentKey());
        pvo.setHide(per.getHide());
        pvo.setLev(per.getLev());
        pvo.setSort(per.getSort());
        return pvo;
    }

//    管理首页("管理首页", "fa-home", "glsy", null, "index", 1, 1),
//
//    站内新闻("站内新闻", "", "znxy", null, null, 1, 10),
//    新闻发布("新闻发布", "", "znxy_xwfb", "znxy", "news/add", 2, 11),
//    新闻列表("新闻列表", "", "znxy_xwlb", "znxy", "news/list",2, 12),
//    默认数据库("默认数据库", "", "znxy_mrsjk", "znxy", "news/list", 2, 13),
//    数据库1("数据库1", "", "znxy_sjk1", "znxy", "news/list1", 2, 14),
//    数据库2("数据库2", "", "znxy_sjk2", "znxy", "news/list2", 2, 15),

//             管理首页	fa-home	index	glsy		1	1	1

    @Override
    public List<PermissionVo> getPermissions(String userId) {
//        List<Permission> permissions = permissionMapper.findPermissionByUserId(userId);
//        List<Permission> permissions = permissionMapper.findPermissionByUserId(userId);
        List<Permission> permissions = new ArrayList<>(Arrays.asList(
                new Permission("管理首页", "fa-home", "index", "glsy", null, 1, 1, 1),
                new Permission("产品管理", null, null, "cpgl", null, 1, 1, 10),
                new Permission("上架产品", null, "product/add", "cpgl_czcp", "cpgl", 1, 2, 11),
                new Permission("产品列表", null, "product/list", "cpgl_cplb", "cpgl", 1, 2, 12),
                new Permission("订单管理", null, null, "ddgl", null, 1, 1, 20),
                new Permission("订单列表", null, "order/list", "ddgl_cplb", "ddgl", 1, 2, 21),
                new Permission("评价管理", null, null, "pjgl", null, 1, 1, 30),
                new Permission("用户列表", null, "comments/list", "pjgl_pjlb", "pjgl", 1, 2, 31),
                new Permission("用户管理", "fa-user", null, "yhgl", null, 1, 1, 40),
                new Permission("用户列表", null, "user/list", "yhgl_yhlb", "yhgl", 1, 2, 41)
//                ,
//                new Permission("文章管理", null, null, "wzgl", null, 1, 1, 50),
//                new Permission("发布文章", null, "article/add", "wzgl_fbwz", "wzgl", 1, 2, 51),
//                new Permission("文章列表", null, "article/list", "wzgl_wzlb", "wzgl", 1, 2, 52)
        ));
        //// TODO: 18-12-7 返回许可（菜单） 都固定，靜态
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }

        if (CollectionUtils.isNotEmpty(permissions)) {

            Map<String, PermissionVo> oneMap = new LinkedHashMap<String, PermissionVo>();// 一级菜单
            Map<String, PermissionVo> twoMap = new LinkedHashMap<String, PermissionVo>();// 二级菜单
            Map<String, PermissionVo> threeMap = new LinkedHashMap<String, PermissionVo>();// 三级菜单

            String parentKey = null, key = null;
            Integer lev = null;
            PermissionVo child = null, parent = null, permissionVo = null;
            for (Permission p : permissions) {
                key = p.getKey();
                lev = p.getLev();
                permissionVo = convertToVo(p);
                if (1 == lev) {// 判断是不是模块
                    oneMap.put(key, permissionVo);
                }
                if (2 == lev) {// 判断是不是菜单分类
                    twoMap.put(key, permissionVo);
                }
                if (3 == lev) {// 判断是不是菜单
                    threeMap.put(key, permissionVo);
                }
            }

            List<PermissionVo> vos = null;

            // 迭代所有3级菜单， 把3级菜单挂在2级菜单分类下面去
            for (Entry<String, PermissionVo> vo : threeMap.entrySet()) {
                child = vo.getValue();// 3级菜单
                parentKey = child.getParentKey();// 获取3级菜单对应的2级菜单KEY，即父节点KEY
                if (twoMap.containsKey(parentKey)) {// 校验当前拿到的2级菜单KEY在twoMap集合中有没有
                    parent = twoMap.get(parentKey);// 获取对应的2级菜单

                    vos = parent.getChildren();// 获取2级菜单下3级菜单集合
                    if (CollectionUtils.isEmpty(vos)) {
                        vos = new ArrayList<>();
                    }
                    vos.add(child);// 将3级菜单挂在2级菜单下去
                    parent.setChildren(vos);
                    twoMap.put(parentKey, parent);
                }
            }

            // 迭代所有2级菜单， 把2级菜单挂在1级菜单分类下面去
            for (Entry<String, PermissionVo> vo : twoMap.entrySet()) {
                child = vo.getValue();// 2级菜单
                parentKey = child.getParentKey();// 获取2级菜单对应的1级菜单KEY，即父节点KEY
                if (oneMap.containsKey(parentKey)) {// 校验当前拿到的1级菜单KEY在oneMap集合中有没有
                    parent = oneMap.get(parentKey);// 获取对应的1级菜单

                    vos = parent.getChildren();// 获取1级菜单下2级菜单集合
                    if (CollectionUtils.isEmpty(vos)) {
                        vos = new ArrayList<PermissionVo>();
                    }
                    vos.add(child);// 将2级菜单挂在1级菜单下去
                    parent.setChildren(vos);
                    oneMap.put(parentKey, parent);
                }
            }

            List<PermissionVo> permissionVos = new ArrayList<PermissionVo>();
            permissionVos.addAll(oneMap.values());
            return permissionVos;
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void addPermission(Permission permission) {
//        if (permission == null || StringUtils.isBlank(permission.getKey()) || StringUtils.isBlank(permission.getName())) {
//            throw new BusinessException("permission-fail", "## 创建菜单出错；菜单项数据不完整，无法进行创建。");
//        }
//        Permission p = permissionMapper.findPermissionByKey(permission.getKey());
//        if (p != null)
//            throw new BusinessException("permission-fail", "#创建菜单出错;菜单Key已经存在,key=" + permission.getKey());
//        permission.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_PERMISSION));
//        permissionMapper.insert(permission);
    }

}
