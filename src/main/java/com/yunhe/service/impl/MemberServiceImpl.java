package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Member;
import com.yunhe.mapper.MemberMapper;
import com.yunhe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/3/003 12:19
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    /**
     * 查询联系人给添加订单做回显
     *
     * @return 返回id和name
     */
    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    /**
     * 查询所有联系人
     *
     * @return 联系人信息
     */
    @Override
    public PageInfo<Member> findMember(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        List<Member> memberList = memberMapper.findAll();
        return new PageInfo<>(memberList);
    }

    /**
     * 根据联系人id查询可添加会员信息
     *
     * @param id 联系人id
     * @return 可添加会员
     */
    @Override
    public Member findMemberVip(Integer id) {
        return memberMapper.findMemberVip(id);
    }

    /**
     * 给联系人添加会员
     *
     * @param memberId 联系人id
     * @param ids      会员id
     */
    @Override
    public void addVip(Integer memberId, Integer[] ids) {
        for (Integer id : ids) {
            memberMapper.addVip(memberId,id);
        }
    }
}
