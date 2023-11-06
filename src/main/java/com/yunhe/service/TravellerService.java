package com.yunhe.service;

import com.yunhe.entity.Traveller;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/3/003 13:52
 */
public interface TravellerService {
    /**
     * 查询游客id和name进行回显
     * @return 返回的游客数据
     */
    List<Traveller> findAll();
}
