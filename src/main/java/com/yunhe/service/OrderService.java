package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Orders;
import org.aspectj.weaver.ast.Or;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 19:10
 */
public interface OrderService {

    /**
     * 分页查询所有订单
     * @param page 起始页码
     * @param size 每页条数
     * @return 返回的订单数据
     */
    PageInfo<Orders> findAll(Integer page,Integer size);

    /**
     * 添加订单信息
     * @param orders 订单信息
     */
    void saveOrder(Orders orders);

    /**
     * 修改订单信息
     * @param orders 订单信息
     */
    void editOrder(Orders orders,String productName,Integer productPrice);

    /**
     * 根据id查询订单详情
     * @param id 订单id
     * @return 返回的订单详情
     */
    Orders findById(Integer id);

    /**
     * 删除订单信息
     * @param id 订单id
     */
    void delById(Integer id);
}
