package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Permission;
import com.yunhe.mapper.PermissionMapper;
import com.yunhe.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 11:04
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 查询所有权限
     *
     * @param page 起始页码
     * @param size 每页条数
     * @return 权限列表
     */
    @Override
    public PageInfo<Permission> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Permission> permissionList = permissionMapper.findAll();
        return new PageInfo<>(permissionList);
    }

    /**
     * 添加权限信息
     *
     * @param permission 权限信息
     */
    @Override
    public void addPermission(Permission permission) {
        permissionMapper.addPermission(permission);
    }

    /**
     * 根据权限id删除权限
     *
     * @param id 权限id
     */
    @Override
    public void delPermission(Integer id) {
        permissionMapper.deletePermission(id);
    }
}
