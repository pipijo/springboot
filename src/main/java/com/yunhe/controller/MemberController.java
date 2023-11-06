package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Member;
import com.yunhe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 13:55
 */
@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 查询联系人列表
     * @param page 起始页
     * @param size 每页条数
     * @param model 数据模型
     * @return 联系人列表
     */
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "10") Integer size, Model model){
        PageInfo<Member> pageInfo = memberService.findMember(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "member-list";
    }

    /**
     * 查询联系人没有的会员名称
     * @param id 联系人id
     * @return 跳转到添加页面
     */
    @RequestMapping("findMemberVip")
    public String findMemberVip(Integer id,Model model){
        Member memberVip = memberService.findMemberVip(id);
        model.addAttribute("member",memberVip);
        return "member-vip-add";
    }

    /**
     * 给联系人添加会员
     * @param memberId 联系人id
     * @param ids 会员id
     * @return 重定向到查询
     */
    @RequestMapping("addVip")
    public String addVip(Integer memberId,Integer[] ids){
        memberService.addVip(memberId,ids);
        return "redirect:findAll";
    }
}
