package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Permission;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 11:02
 */
public interface PermissionService {

    /**
     * 查询所有权限
     * @param page 起始页码
     * @param size 每页条数
     * @return 权限列表
     */
    PageInfo<Permission> findAll(Integer page,Integer size);

    /**
     * 添加权限信息
     * @param permission 权限信息
     */
    void addPermission(Permission permission);


    /**
     * 根据权限id删除权限
     * @param id 权限id
     */
    void delPermission(Integer id);
}
