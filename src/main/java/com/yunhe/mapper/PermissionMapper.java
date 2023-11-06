package com.yunhe.mapper;

import com.yunhe.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/5/005 10:45
 */
@Mapper
public interface PermissionMapper {


    /**
     * 用户详情做回显
     * @param id 用户id
     * @return 信息
     */
    List<Permission> findPermissions(Integer id);

    /**
     * 根据id查询可添加权限，给角色表做回显
     * @param id 角色id
     * @return 可添加权限
     */
//    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findPer(Integer id);

    /**
     * 根据角色id查询角色详情
     * @param id 角色id
     * @return 角色信息
     */
    List<Permission> findPermission(Integer id);

    /**
     * 查询所有权限
     * @return 权限信息
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 添加权限信息
     * @param permission 权限信息
     */
    @Insert("insert into permission values (null,#{permissionName},#{url})")
    void addPermission(Permission permission);

    /**
     * 根据权限id删除权限
     * @param id 权限id
     */
    @Delete("delete from permission where id = #{id}")
    void deletePermission(Integer id);
}
