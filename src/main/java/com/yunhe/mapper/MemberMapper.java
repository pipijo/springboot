package com.yunhe.mapper;

import com.yunhe.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 无意
 * @description 联系人
 * @create 2023/11/3/003 12:03
 */
@Mapper
public interface MemberMapper {
    /**
     * 查询所有联系人
     * @return 返回所有联系人
     */
    List<Member> findAll();

    /**
     * 注解方式给订单详情回显查询
     * @param id 关联id
     * @return 返回查询结果
     */
    @Select("select * from member where id = #{memberId}")
    Member findMember(Integer id);

    /**
     * 根据联系人id查询会员
     * @param id 联系人id
     * @return 可添加的会员
     */
    Member findMemberVip(Integer id);

    /**
     * 给联系人和会员的中间表中添加关联关系
     * @param id 联系人id
     * @param i 会员id
     */
    @Insert("insert into member_vip values (#{id},#{i})")
    void addVip( Integer id,Integer i);


    /**
     * 删除联系人
     * @param id 联系人id
     */
    @Delete("delete from member where id = #{id}")
    void delMember(Integer id);

    /**
     * 删除联系人和vip的中间表关系
     * @param id 联系人id
     */
    @Delete("delete from member_vip where memberId = #{id}")
    void delMemberVip(Integer id);
}
