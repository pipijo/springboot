package com.yunhe.service.impl;

import com.yunhe.entity.Traveller;
import com.yunhe.mapper.TravellerMapper;
import com.yunhe.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/3/003 13:53
 */
@Service
public class TravellerServiceImpl implements TravellerService {
    @Autowired
    private TravellerMapper travellerMapper;
    /**
     * 查询游客id和name进行回显
     *
     * @return 返回的游客数据
     */
    @Override
    public List<Traveller> findAll() {
        return travellerMapper.findAll();
    }
}
