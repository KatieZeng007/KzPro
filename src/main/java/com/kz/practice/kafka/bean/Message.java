package com.kz.practice.kafka.bean;

import lombok.Data;

import java.util.Date;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:05 2019/12/25
 * @ Description：发送消息实体
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Data
public class Message {

    private String id;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 时间戳
     */
    private Date time;

}
