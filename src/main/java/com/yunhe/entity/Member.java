package com.yunhe.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 18:48
 */
@Data
public class Member {
    private String id;
    private String name;
    private String nickName;
    private String phoneNum;
    private String email;
    List<Vip> vip;
}
