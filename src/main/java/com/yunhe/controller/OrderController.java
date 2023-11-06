package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.yunhe.entity.Member;
import com.yunhe.entity.Orders;
import com.yunhe.entity.Product;
import com.yunhe.entity.Traveller;
import com.yunhe.mapper.MemberMapper;
import com.yunhe.service.OrderService;
import com.yunhe.service.ProductService;
import com.yunhe.service.TravellerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 18:57
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TravellerService travellerService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ProductService productService;

    /**
     * 查询订单表信息
     * @param page 起始页码
     * @param size 每页条数
     * @param model 数据模型
     * @return 返回的订单信息
     */
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size, Model model) {
        PageInfo<Orders> pageInfo = orderService.findAll(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "order-list";
    }

    /**
     * 查询数据做添加订单的回显
     * @return 回显的数据
     */
    @RequestMapping("add")
    public String addOrder(Model model) {
        List<Product> productList = productService.findAll();
        List<Traveller> travellerList = travellerService.findAll();
        List<Member> memberList = memberMapper.findAll();
        model.addAttribute("productList",productList);
        model.addAttribute("memberList",memberList);
        model.addAttribute("travellerList",travellerList);
        return "order-add";
    }

    /**
     * 添加订单信息
     * @param orders 订单信息
     * @return 重定向到查询
     */
    @RequestMapping("save")
    public String saveOrder(Orders orders){
        orderService.saveOrder(orders);
        return "redirect:findAll";
    }

    /**
     * 查询订单详情
     * @param id 订单id
     * @param model 数据模型
     * @return 返回的数据类型
     */
    @RequestMapping("findById")
    public String findById(Integer id,Model model){
        Orders order = orderService.findById(id);
        model.addAttribute("order",order);
        return "order-show";
    }

    /**
     * 回显到修改页面
     * @param id 修改的id
     * @return 跳转到修改页面
     */
    @RequestMapping("editById")
    public String toEdit(Integer id,Model model){
        Orders orders = orderService.findById(id);
        model.addAttribute("orders",orders);
        return "order-edit";
    }

    /**
     * 修改订单信息
     * @param orders 订单数据
     * @return 重定向到查询
     */
    @RequestMapping("edit")
    public String editOrder(Orders orders,String productName,Integer productPrice){
        orderService.editOrder(orders,productName,productPrice);
        return "redirect:findAll";
    }

    /**
     * 删除订单信息
     * @param id 订单id
     * @return 重定向到查询
     */
    @RequestMapping("deleteById")
    public String deleteById(Integer id){
        orderService.delById(id);
        return "redirect:findAll";
    }

    /**
     * 批量删除
     * @param ids 删除的id
     * @return 重定向到查询
     */
    @RequestMapping("delAll")
    public String selectDelete(Integer[] ids){
        for (Integer id : ids) {
            deleteById(id);
        }
        return "redirect:findAll";
    }


}
