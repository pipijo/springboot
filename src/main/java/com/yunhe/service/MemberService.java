package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Member;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/3/003 12:18
 */
public interface MemberService {
    /**
     * 查询联系人给添加订单做回显
     * @return 返回id和name
     */
    List<Member> findAll();


    /**
     * 查询所有联系人
     * @return 联系人信息
     */
    PageInfo<Member> findMember(Integer page,Integer size);

    /**
     * 根据联系人id查询可添加会员信息
     * @param id 联系人id
     * @return 可添加会员
     */
    Member findMemberVip(Integer id);


    /**
     * 给联系人添加会员
     * @param memberId 联系人id
     * @param ids 会员id
     */
    void addVip(Integer memberId,Integer[] ids);

    /**
     * 删除联系人信息
     * @param id 联系人id
     */
    void delMember(Integer id);
}
