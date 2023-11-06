package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Role;
import com.yunhe.entity.UserInfo;
import com.yunhe.mapper.UserMapper;
import com.yunhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/4/004 14:32
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Resource(name = "passwordEncoder")
    @Lazy
    private PasswordEncoder passwordEncoder;

    /**
     * 查询所有用户信息
     *
     * @return 返回的用户信息
     */
    @Override
    public PageInfo<UserInfo> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserInfo> list = userMapper.findAll();
        return new PageInfo<>(list);
    }

    /**
     * 给用户添加角色回显
     * @param id 用户id
     * @return 角色信息
     */
    @Override
    public UserInfo findRole(Integer id) {
        return userMapper.findUserByIdAndAllRole(id);
    }

    /**
     * 给用户添加角色
     *
     * @param id  用户id
     * @param ids 角色id
     */
    @Override
    public void addUserRoles(Integer id, Integer[] ids) {
        for (Integer rid : ids) {
            userMapper.addUserRole(id,rid);
        }
    }


    /**
     * 添加用户信息
     *
     * @param userInfo 用户信息
     */
    @Override
    @Transactional
    public void addUser(UserInfo userInfo) {
        String password = userInfo.getPassword();
        password = passwordEncoder.encode(password);
        userInfo.setPassword(password);
        userMapper.addUser(userInfo);
        String id = userInfo.getId();
        userMapper.addUR(id);
    }

    /**
     * 根据用户id查询用户详情
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public UserInfo findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     */
    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userMapper.delUserRole(id);
        userMapper.deleteUser(id);
    }

    /**
     * 根据用户名加载用户的详细信息，包括用户的角色。
     *
     * @param username 用户名
     * @return 包含用户详细信息的 UserDetails 对象
     * @throws UsernameNotFoundException 如果未找到指定用户名的用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用查询方法
        UserInfo userInfo = userMapper.findByUserName(username);

        // 用户的角色
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : userInfo.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        return new User(
                userInfo.getUsername(),      // 用户名
                userInfo.getPassword(),      // 加密后的密码
                userInfo.getStatus() == 1,   // 用户账号是否启用（1为启用，0为禁用）
                true,                       // 账号是否未过期
                true,                       // 账号是否未锁定
                true,                       // 凭证是否未过期
                grantedAuthorities           // 用户的权限/角色
        );
    }



}
