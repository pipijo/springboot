package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Role;
import com.yunhe.entity.UserInfo;
import com.yunhe.service.RoleService;
import com.yunhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/4/004 14:25
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @param page 起始页码
     * @param size 每页调试
     * @param model 数据模型
     * @return 返回的数据
     */
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size, Model model){
        PageInfo<UserInfo> pageInfo = userService.findAll(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "user-list";
    }

    /**
     * 添加用户信息
     * @param userInfo 用户信息
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN","GroupManger"}) // 用户权限
    @RequestMapping("addUser")
    public String addUser(UserInfo userInfo){
        userService.addUser(userInfo);

        return "redirect:findAll";
    }

    /**
     * 查询用户详情
     * @param id 用户id
     * @param model 数据模型
     * @return 跳转到详情页面
     */
    @RequestMapping("findById")
    public String findById(Integer id,Model model) {
        UserInfo byId = userService.findById(id);
        model.addAttribute("user",byId);
        return "user-show";
    }

    /**
     * 添加角色数据回显
     * @param id 用户id
     * @return 跳转到添加页面
     */
    @RolesAllowed({"ADMIN","GroupManger"})
    @RequestMapping("findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(Integer id,Model model){
        UserInfo userInfo = userService.findRole(id);
        model.addAttribute("user",userInfo);
        return "user-role-add";
    }

    /**
     * 给用户添加角色
     * @param userId 用户id
     * @param ids 角色id
     * @return 角色数据
     */
    @RequestMapping("addRoleToUser")
    public String addRoleToUser(Integer userId,Integer[] ids) {
        userService.addUserRoles(userId,ids);
        return "redirect:findAll";
    }

    /**
     * 根据用户id删除用户
     * @param id 用户id
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("deleteUser")
    public String deleteUser(Integer id) {
        userService.deleteUser(id);
        return "redirect:findAll";
    }

    /**
     * 根据用户id批量删除用户
     * @param ids 用户id
     * @return 重定向到查询
     */
    @RolesAllowed({"ADMIN"})
    @RequestMapping("deleteAll")
    public String deleteUser(Integer[] ids) {
        for (Integer id : ids) {
            userService.deleteUser(id);
        }
        return "redirect:findAll";
    }

    /**
     * 回显登录用户名
     * @return 用户名
     */
    @RequestMapping("getUsername")
    @ResponseBody
    public String getUsername(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }
}
