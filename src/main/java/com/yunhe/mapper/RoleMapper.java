package com.yunhe.mapper;


import com.yunhe.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/5/005 10:45
 */
@Mapper
public interface RoleMapper {

    //    查询可添加角色
    @Select("select * from role where id not in (select ur.roleId from users_role ur where ur.userId = #{id})")
    List<Role> findRoles(Integer id);

    /**
     * 查询所有角色
     * @return 角色信息
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色信息
     * @param role 角色信息
     */
    @Insert("insert into role values (null,#{roleName},#{roleDesc})")
    void addRole(Role role);

    /**
     * 查询角色详情
     * @param id 角色id
     * @return 角色详情
     */
    Role roleShow(Integer id);

    /**
     * 根据角色id查询可添加权限
     * @param id 角色id
     * @return 可添加权限
     */
/*    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissions",javaType = List.class,many = @Many(select = "com.yunhe.mapper.PermissionMapper.findPer"),column = "id")
    })*/
    Role addRolePer(Integer id);


    /**
     * 添加角色權限
     * @param id 權限id
     * @param rid 角色id
     */
    @Insert("insert into role_permission (permissionId, roleId) values (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId") Integer id,@Param("roleId") Integer rid);

    /**
     * 根据用户id查询详情
     * @param id 用户id
     * @return 信息
     */
    List<Role> findRole(Integer id);


    /**
     * 根据角色id删除角色
     * @param id 角色id
     */
    @Delete("delete from role where id = #{id}")
    void deleteRole(Integer id);

    /**
     * 删除角色和权限的中间表
     * @param id 角色id
     */
    @Delete("delete from role_permission where roleId = #{id}")
    void delRolePer(Integer id);

    /**
     * 删除角色表和用户表的中间表
     * @param id 角色id
     */
    @Delete("delete from users_role where roleId = #{id}")
    void delRoleUser(Integer id);


    /**
     * 根据用户id查询可添加角色
     * @param id 用户id
     * @return 角色信息
     */
    List<Role> findUserByIdAndAllRole(Integer id);
}
