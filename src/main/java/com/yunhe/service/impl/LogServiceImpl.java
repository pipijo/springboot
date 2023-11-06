package com.yunhe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhe.entity.SysLog;
import com.yunhe.mapper.LogMapper;
import com.yunhe.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 12:02
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    /**
     * 查询所有日志
     *
     * @param page 起始页码
     * @param size 每页条数
     * @return 日志记录
     */
    @Override
    public PageInfo<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<SysLog> logList = logMapper.findAll();
        return new PageInfo<>(logList);
    }
}
