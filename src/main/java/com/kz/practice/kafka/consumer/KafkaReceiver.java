package com.kz.practice.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:21 2019/12/25
 * @ Description：kafka消息接收
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Component
@Slf4j
public class KafkaReceiver {

    /**
     *     topics是一个数组可以监听多个主题
     */
//    @KafkaListener(topics = {"kz"})
//    public void listen(ConsumerRecord<?,?> record){
//        Optional kafkaMessage = Optional.ofNullable(record.value());
//        if(kafkaMessage.isPresent()){
//            Object message = kafkaMessage.get();
//            log.info("-------------record="+record);
//            log.info("-------------message="+message);
//        }
//    }

}
