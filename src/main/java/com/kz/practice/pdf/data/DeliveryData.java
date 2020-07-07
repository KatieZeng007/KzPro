package com.kz.practice.pdf.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 填充pdf的数据
 * @Author KatieZ
 * @Date Created in 15:39  15:39
 */
@Data
public class DeliveryData implements Serializable {
    /**
     * 单号
     */
    private String deliveryNo;
    /**
     * 单据日期
     */
    private Date deliveryDate;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverMobile;
    /**
     * 导师
     */
    private String teacherName;
    /**
     * 收货地址
     */
    private String deliveryAddress;
    /**
     * 配送员
     */
    private String deliveryName;
    /**
     * 打印次数
     */
    private Integer printNum;
    /**
     * 条形码 base64格式
     */
    private String barCode;
    /**
     * 订单详情
     */
    private List<Detail> detailList;



}
