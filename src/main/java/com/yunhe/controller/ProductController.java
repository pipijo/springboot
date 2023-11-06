package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.Product;
import com.yunhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 10:28
 */
@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 分页查询所有产品
     * @param page 起始页码
     * @param size 每页条数
     * @param model 数据模型
     * @return 跳转的页面
     */
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size, Model model) {
        PageInfo<Product> pageInfo = productService.findAll(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "product-list";
    }

    /**
     * 添加产品
     * @param product 产品信息
     * @return 重定向到查询
     */
    @RequestMapping("save")
    public String addProduct(Product product){
        productService.addProduct(product);
        return "redirect:findAll";
    }

    /**
     * 根据产品id查询做回显效果
     * @param id 查询的id
     * @param model 数据模型
     * @return 返回的产品数据
     */
    @RequestMapping("editById")
    public String editById(Integer id,Model model){
        Product product = productService.editById(id);
        model.addAttribute("product",product);
        return "product-edit";
    }

    /**
     * 修改产品信息
     * @param product 产品信息
     * @return 重定向到查询
     */
    @RequestMapping("edit")
    public String edit(Product product){
        productService.edit(product);
        return "redirect:findAll";
    }

    /**
     * 根据id删除产品
     * @param id 删除的产品id
     * @return 重定向到查询
     */
    @RequestMapping("deleteById")
    public String deleteById(Integer id){
        productService.deleteById(id);
        return "redirect:findAll";
    }

    /**
     * 删除选中的产品
     * @param ids 选中的产品id
     * @return 重定向到查询
     */
    @RequestMapping("selectDelete")
    public String delAll(Integer[] ids){
        for (Integer id : ids) {
            productService.deleteById(id);
        }
        return "redirect:findAll";
    }

    /**
     * 详情回显
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("findById")
    public String findById(Integer id,Model model){
        Product product = productService.editById(id);
        model.addAttribute("product",product);
        return "product-show";
    }
}
