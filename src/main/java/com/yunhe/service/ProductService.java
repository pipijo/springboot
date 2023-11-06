package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Product;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 10:31
 */
public interface ProductService {
    /**
     * 分页查询所有产品
     * @param page 起始页码
     * @param size 每页条数
     * @return 返回的产品列表
     */
    PageInfo<Product> findAll(Integer page,Integer size);

    /**
     * 给添加订单做回显
     * @return 返回的数据
     */
    List<Product> findAll();

    /**
     * 添加产品
     * @param product 产品信息
     */
    void addProduct(Product product);

    /**
     * 根据id查询产品做回显效果
     * @param id 查询的id
     * @return 返回的产品数据
     */
    Product editById(Integer id);

    /**
     * 修改产品信息
     * @param product 产品信息
     */
    void edit(Product product);

    /**
     * 根据id删除产品
     * @param id 删除的产品id
     */
    void deleteById(Integer id);

}
