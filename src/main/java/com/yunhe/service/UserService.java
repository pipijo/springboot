package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Role;
import com.yunhe.entity.UserInfo;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/4/004 14:31
 */

public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户信息
     * @return 返回的用户信息
     */
    PageInfo<UserInfo> findAll(Integer page,Integer size);

    /**
     * 添加用户信息
     * @param userInfo 用户信息
     */
    void addUser(UserInfo userInfo);


    /**
     * 给用户添加角色回显
     * @param id 用户id
     * @return 角色信息
     */
    UserInfo findRole(Integer id);

    /**
     * 给用户添加角色
     * @param id 用户id
     * @param ids 角色id
     */
    void addUserRoles(Integer id, Integer[] ids);


    /**
     * 根据用户id查询用户详情
     * @param id 用户id
     * @return 用户信息
     */
    UserInfo findById(Integer id);


    /**
     * 根据用户id删除用户
     * @param id 用户id
     */
    void deleteUser(Integer id);
}
