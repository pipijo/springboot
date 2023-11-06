package com.yunhe.mapper;

import com.yunhe.entity.Traveller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 无意
 * @description 游客
 * @create 2023/11/3/003 12:01
 */
@Mapper
public interface TravellerMapper {
    /**
     * 查询所有游客
     * @return 返回所有游客信息
     */
    List<Traveller> findAll();

    /**
     * 注解方式给订单详情做回显
     * @param id 订单id
     * @return 返回的查询结果
     */
    @Select("select * from traveller t,order_traveller ot where ot.travellerId = t.id and ot.orderId = #{id}")
    Traveller findTraveller(Integer id);
}
