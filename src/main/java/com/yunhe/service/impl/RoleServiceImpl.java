package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Role;
import com.yunhe.entity.UserInfo;
import com.yunhe.mapper.RoleMapper;
import com.yunhe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/5/005 18:00
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色
     *
     * @param page
     * @param size
     * @return 角色信息
     */
    @Override
    public PageInfo<Role> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Role> roleList = roleMapper.findAll();
        return new PageInfo<>(roleList);
    }

    /**
     * 添加角色信息
     *
     * @param role 角色信息
     */
    @Override
    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    /**
     * 查询角色详情
     *
     * @param id 角色id
     * @return 角色信息
     */
    @Override
    public Role roleShow(Integer id) {
        return roleMapper.roleShow(id);
    }

    /**
     * 根据角色id查询可添加权限
     *
     * @param id 角色id
     * @return 可添加的角色
     */
    @Override
    public Role addRolePer(Integer id) {
        return roleMapper.addRolePer(id);
    }

    /**
     * 根据角色id删除角色
     *
     * @param id 角色id
     */
    @Override
    public void delRole(Integer id) {
        // 删除角色和权限的中间表
        roleMapper.delRolePer(id);
        // 删除角色和用户的中间表
        roleMapper.delRoleUser(id);
        // 删除角色
        roleMapper.deleteRole(id);
    }
}
