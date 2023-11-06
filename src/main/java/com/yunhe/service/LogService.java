package com.yunhe.service;

import com.github.pagehelper.PageInfo;
import com.yunhe.entity.SysLog;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 12:01
 */
public interface LogService {

    /**
     * 查询所有日志
     * @param page 起始页码
     * @param size 每页条数
     * @return 日志记录
     */
    PageInfo<SysLog> findAll(Integer page,Integer size);
}
