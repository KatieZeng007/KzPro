package com.kz.practice.test;

import com.kz.practice.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 10:08  10:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestRedis {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test(){
        redisUtil.hset("user:100","username","kz");
        log.info(redisUtil.hget("user:100","username").toString());
    }
}
