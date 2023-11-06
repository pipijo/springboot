package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Role;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/5/005 18:00
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @return 角色信息
     */
    PageInfo<Role> findAll(Integer page,Integer size);

    /**
     * 添加角色信息
     * @param role 角色信息
     */
    void addRole(Role role);

    /**
     * 查询角色详情
     * @param id 角色id
     * @return 角色信息
     */
    Role roleShow(Integer id);


    /**
     * 根据角色id查询可添加权限
     * @param id 角色id
     * @return 可添加的角色
     */
    Role addRolePer(Integer id);

    /**
     * 根据角色id删除角色
     * @param id 角色id
     */
    void delRole(Integer id);
}
