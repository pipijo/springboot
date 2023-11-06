package com.yunhe.mapper;

import com.yunhe.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 无意
 * @description 产品
 * @create 2023/11/2/002 10:29
 */
@Mapper
public interface ProductMapper {

    /**
     * 查询所有产品
     * @return 返回产品列表
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 添加产品
     * @param product 产品信息
     */
    @Insert("insert into product values (null,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void addProduct(Product product);

    @Select("select * from product where id = #{id}")
    Product findProduct(Integer id);

    /**
     * 根据id查询产品做回显效果
     * @param id 查询的id
     * @return 返回产品的信息
     */
    @Select("select * from product where id = #{id}")
    Product editById(Integer id);

    @Update("update product set productNum = #{productNum},productName = #{productName},cityName = #{cityName}," +
            "departureTime = #{departureTime},productPrice = #{productPrice},productDesc = #{productDesc},productStatus = #{productStatus} where id = #{id}")
    void edit(Product product);

    /**
     * 根据id删除产品
     * @param id 删除的产品id
     */
    @Delete("delete from product where id = #{id}")
    void deleteById(Integer id);


    @Update("update product set productName = #{productName},productPrice = #{productPrice} where id = #{productId}")
    void editOT(@Param("productName") String productName,@Param("productPrice") Integer productPrice,@Param("productId") Integer productId);
}
