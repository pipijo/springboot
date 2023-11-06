package com.yunhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/2/002 10:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;  // 产品ID
    private String productNum;  // 产品编号
    private String productName;  // 产品名称
    private String cityName;  // 城市名称
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;  // 出发时间
    private String departureTimeStr;  // 格式化后的出发时间字符串
    private Integer productPrice;  // 产品价格
    private String productDesc;  // 产品描述
    private Integer productStatus;  // 产品状态（0表示关闭，1表示开启）
    private String productStatusStr;  // 产品状态的字符串表示

    // 自定义setter方法，将字符串格式的出发时间转换为Date类型
    public void setDepartureTimeStr(String departureTimeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(departureTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.departureTime = date;
    }

    // 自定义getter方法，将出发时间格式化为字符串
    public String getDepartureTimeStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (null != departureTime) {
            departureTimeStr = dateFormat.format(departureTime);
        }
        return departureTimeStr;
    }

    // 自定义setter方法，根据产品状态字符串设置产品状态
    public void setProductStatusStr(String productStatusStr) {
        if (productStatusStr.equals("关闭")) {
            this.productStatus = 0;
        } else if (productStatusStr.equals("开启")) {
            this.productStatus = 1;
        }
    }

    // 自定义getter方法，根据产品状态设置产品状态字符串
    public String getProductStatusStr() {
        if (productStatus == null) {
            return "";
        }

        if (productStatus == 0) {
            productStatusStr = "关闭";
        } else if (productStatus == 1) {
            productStatusStr = "开启";
        }

        return productStatusStr;
    }
}
