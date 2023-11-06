package com.yunhe.mapper;

import com.yunhe.entity.Role;
import com.yunhe.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 无意
 * @description 用户表查询
 * @create 2023/11/4/004 14:29
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户数据
     * @return 返回数据
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 根据用户名查询用户信息，做登录
     * @param userName 用户名
     * @return 用户信息
     */
    UserInfo findByUserName(String userName);

    /**
     * 添加用户
     * @param userInfo 用户信息
     */
//    @Insert("insert into users values (null,#{email},#{username},#{password},#{phoneNum},#{status})")
    void addUser(UserInfo userInfo);

    /**
     * 添加用户之后添加默认用户权限
     * @param id 用户id
     */
    @Insert("insert into users_role values (#{id},2)")
    void addUR(String id);

    /**
     * 给用户添加角色回显
     * @param id 用户id
     * @return 用户数据
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",javaType = List.class,many = @Many(select = "com.yunhe.mapper.RoleMapper.findRoles"),column = "id")
    })
    UserInfo findUserByIdAndAllRole(Integer id);


    /**
     * 给用户添加角色
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Insert("insert into users_role (userId, roleId) values (#{userId},#{roleId})")
    void addUserRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    /**
     * 根据用户id查询用户详情信息
     * @param id 用户id
     * @return 返回的用户信息
     */
    UserInfo findById(Integer id);


    /**
     * 跟根据用户id删除用户
     * @param id 用户id
     */
    void deleteUser(Integer id);

    /**
     * 删除用户和角色的中间表
     * @param userId 用户id
     */
    void delUserRole(Integer userId);
}
