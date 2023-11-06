package com.yunhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/9/6/006 9:46
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysLog {
    private String id;
    private Date visitTime;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String method;
}
