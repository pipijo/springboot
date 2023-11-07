package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Role;
import com.yunhe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 8:56
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色信息
     * @param page 起始页码
     * @param size 每页条数
     * @param model 数据模型
     * @return 角色信息
     */
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size,Model model) {
        PageInfo<Role> pageInfo = roleService.findAll(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "role-list";
    }

    /**
     * 添加角色信息
     * @param role 角色信息
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("addRole")
    public String addRole(Role role) {
        roleService.addRole(role);
        return "redirect:findAll";
    }

    /**
     * 查询角色详情
     * @param id 角色id
     * @param model 数据模型
     * @return 角色信息
     */
    @RequestMapping("findById")
    public String findById(Integer id,Model model) {
        Role role = roleService.roleShow(id);
        model.addAttribute("role",role);
        return "role-show";
    }

    /**
     * 查询角色可添加权限
     * @param id 角色id
     * @param model 数据模型
     * @return 可添加权限
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(Integer id,Model model){
        Role role = roleService.addRolePer(id);
        model.addAttribute("role",role);
        return "role-permission-add";
    }

    /**
     * 添加角色權限
     * @param ids 權限id
     * @param roleId 角色id
     * @return 重定向到查詢
     */
    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(Integer[] ids,Integer roleId){
        roleService.addPermissionToRole(ids,roleId);
        return "redirect:findAll";
    }


    /**
     * 根据角色id删除角色信息
     * @param id 角色id
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN","GroupManger"})
    @RequestMapping("deleteRole")
    public String deleteRole(Integer id) {
        roleService.delRole(id);
        return "redirect:findAll";
    }


    /**
     * 批量删除角色信息
     * @param ids 角色id
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN","GroupManger"})
    @RequestMapping("delAll")
    public String delAll(Integer[] ids){
        for (Integer id : ids) {
            roleService.delRole(id);
        }
        return "redirect:findAll";
    }
}
