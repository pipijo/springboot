package com.yunhe.mapper;

import com.yunhe.entity.Member;
import com.yunhe.entity.Orders;
import com.yunhe.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * @author 无意
 * @description 订单
 * @create 2023/11/2/002 19:08
 */
@Mapper
public interface OrderMapper {
    @Update("update orders set productId = null where productId = #{id}")
    void delOrders( Integer id);
    /**
     * 查询所有订单
     * @return 返回订单信息
     */
    List<Orders> findAll();

    /**
     * 添加订单信息
     * @param orders 订单信息
     */
    void saveOrder(Orders orders);

    /**
     * 查询订单详情
     * @param id 订单id
     * @return 返回的订单信息
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "product",javaType = Product.class,one = @One(select = "com.yunhe.mapper.ProductMapper.editById"),column = "productId"),

            @Result(property = "member",javaType = Member.class,one = @One(select = "com.yunhe.mapper.MemberMapper.findMember"),column = "memberId"),

            @Result(property = "travellers",javaType = List.class,many = @Many(select = "com.yunhe.mapper.TravellerMapper.findTraveller"),column = "id")
    })
    Orders findById(Integer id);


    @Select("select productId from orders where id = #{id}")
    Orders findOne(String id);

    /**
     * 修改订单信息
     * @param orders 订单信息
     */
    void editOrder(Orders orders);

    /**
     * 添加订单和游客的关联表
     * @param id 订单id
     * @param travellerId 游客id
     */
    void addOT(@Param("orderId") String id, @Param("travellerId") Integer travellerId);
    /**
     * 删除订单信息
     * @param id 订单id
     */
    void delById(Integer id);

    /**
     * 删除中间表
     * @param id 订单id
     */
    void delOT(Integer id);
}
