package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Product;
import com.yunhe.mapper.OrderMapper;
import com.yunhe.mapper.ProductMapper;
import com.yunhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 10:32
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 分页查询所有产品
     *
     * @param page 起始页码
     * @param size 每页条数
     * @return 返回的产品列表
     */
    @Override
    public PageInfo<Product> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Product> productList = productMapper.findAll();
        return new PageInfo<>(productList);
    }

    /**
     * 给添加订单做回显
     *
     * @return 返回的数据
     */
    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    /**
     * 添加产品
     *
     * @param product 产品信息
     */
    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    /**
     * 根据id查询产品做回显效果
     *
     * @param id 查询的id
     * @return 返回的产品数据
     */
    @Override
    public Product editById(Integer id) {
        return productMapper.editById(id);
    }

    /**
     * 修改产品信息
     *
     * @param product 产品信息
     */
    @Override
    public void edit(Product product) {
        productMapper.edit(product);
    }

    /**
     * 根据id删除产品
     *
     * @param id 删除的产品id
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        orderMapper.delOrders(id);
        productMapper.deleteById(id);
    }

}
