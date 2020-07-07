package com.kz.practice;

import com.kz.practice.entity.Person;
import com.kz.practice.entity.Student;
import com.kz.practice.kafka.provider.KafkaSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PracticeApplication {

	public static final String CURRENT_VERSION = "v1";

	public static void main(String[] args) {
		 SpringApplication.run(PracticeApplication.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(PracticeApplication.class, args);
//		KafkaSender kafkaSender = context.getBean(KafkaSender.class);
//		for(int i=0;i<3;i++){
//			kafkaSender.send();
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
