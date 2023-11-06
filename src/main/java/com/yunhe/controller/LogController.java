package com.yunhe.controller;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.SysLog;
import com.yunhe.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 11:54
 */
@Controller
@RequestMapping("syslog")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 查询所有日志
     * @param page 起始页码
     * @param size 每页条数
     * @param model 数据模型
     * @return 日志记录
     */
    @RequestMapping("findAll")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "10") Integer size, Model model){
        PageInfo<SysLog> pageInfo = logService.findAll(page, size);
        model.addAttribute("pageInfo",pageInfo);
        return "syslog-list";
    }
}
