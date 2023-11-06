package com.yunhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 12:14
 */
@Controller
@RequestMapping("to")
public class PageController {

    @RequestMapping("{page}")
    public String page(@PathVariable String page) {
        return page;
    }
}
