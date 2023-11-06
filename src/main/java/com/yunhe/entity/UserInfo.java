package com.yunhe.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Data
public class UserInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private int status;
    private String statusStr;
    private List<Role> roles;

    public String getStatusStr() {
        if(status == 0){
            statusStr ="关闭";
        }else if(status == 1){
            statusStr ="开启";
        }
        return statusStr;
    }
}