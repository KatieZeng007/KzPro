package com.kz.practice.copy;

import com.kz.practice.entity.Person;
import com.kz.practice.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 14:31 2020/1/7
 * @ Description：
 * @ Modified By：KatieZ
 * @Version: V
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@Slf4j
//public class TestCopyUtil {
//
//    @Test
//    public void testFillNum(){
//        Student student = new Student();
//        Person person = new Person();
//        person.setName("站三");
//        person.setTest("1");
//        person.setText("四六");
//        BeanUtils.copyProperties(student,person);
//        System.out.println(student.getName());
//    }
//}
