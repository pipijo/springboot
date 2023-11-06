package com.yunhe.mapper;

import com.yunhe.entity.Vip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 15:13
 */
@Mapper
public interface VipMapper {

    /**
     * 根据联系人id查询会员信息，做添加回显
     * @param id 联系人id
     * @return 会员信息
     */
    @Select(" select * from vip where id not in (select vipId from member_vip where memberId = #{id})")
    List<Vip> findById(Integer id);
}
