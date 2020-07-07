package com.kz.practice.kafka.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kz.practice.kafka.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:07 2019/12/25
 * @ Description：消息发送者
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Component
@Slf4j
public class KafkaSender {

//    @Autowired
//    private KafkaTemplate<String,String> template;
//    private Gson gson = new GsonBuilder().create();
//
//    // 发送消息
//    public void send(){
//        Message message = new Message();
//        message.setId(UUID.randomUUID().toString());
//        message.setMsg("kafka发送消息了");
//        message.setTime(new Date());
//        log.info("message：{}",gson.toJson(message));
//        // kz为topic topic在程序中不需要提前在kafka中设置，发送的时候会自动创建设置的topic
//        template.send("kz",gson.toJson(message));
//    }
}
