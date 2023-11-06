package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Permission;
import com.yunhe.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 10:58
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @param page 起始页码
     * @param size 每页条数
     * @param model 数据模型
     * @return 权限信息
     */
    @RolesAllowed({"ADMIN","GroupManger"})
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size, Model model) {
        PageInfo<Permission> pageInfo = permissionService.findAll(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "permission-list";
    }

    /**
     * 添加权限信息
     * @param permission 权限信息
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("addPermission")
    public String addPermission(Permission permission) {
        permissionService.addPermission(permission);
        return "redirect:findAll";
    }

    /**
     * 根据权限id删除权限
     * @param id 权限id
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("deletePermission")
    public String deletePermission(Integer id) {
        permissionService.delPermission(id);
        return "redirect:findAll";
    }

    /**
     * 批量删除权限
     * @param ids 权限id数组
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("delAll")
    public String delAll(Integer[] ids){
        for (Integer id : ids) {
            permissionService.delPermission(id);
        }
        return "redirect:findAll";
    }
}
