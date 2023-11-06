package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Orders;
import com.yunhe.mapper.OrderMapper;
import com.yunhe.mapper.ProductMapper;
import com.yunhe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 19:11
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 分页查询所有订单
     *
     * @param page 起始页码
     * @param size 每页条数
     * @return 返回的订单数据
     */
    @Override
    public PageInfo<Orders> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Orders> ordersList = orderMapper.findAll();
        return new PageInfo<>(ordersList);
    }

    /**
     * 添加订单信息
     *
     * @param orders 订单信息
     */
    @Override
    @Transactional
    public void saveOrder(Orders orders) {
        // 添加订单信息
        orderMapper.saveOrder(orders);
        // 获取订单id
        String orderId = orders.getId();
        // 获取添加的游客id
        for (Integer travellerId : orders.getTravellerId()) {
            // 给中间表添加关联关系
            orderMapper.addOT(orderId,travellerId);
        }
    }

    /**
     * 修改订单信息
     *
     * @param orders 订单信息
     */
    @Override
    @Transactional
    public void editOrder(Orders orders,String productName,Integer productPrice) {
        orderMapper.editOrder(orders);
        Orders order = orderMapper.findOne(orders.getId());
        Integer id = order.getProductId();
        productMapper.editOT(productName,productPrice,id);
    }


    /**
     * 根据id查询订单详情
     *
     * @param id 订单id
     * @return 返回的订单详情
     */
    @Override
    public Orders findById(Integer id) {
        return orderMapper.findById(id);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单id
     */
    @Override
    @Transactional // 开启事务
    public void delById(Integer id) {
        orderMapper.delOT(id);
        orderMapper.delById(id);
    }
}
