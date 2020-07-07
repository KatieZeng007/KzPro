package com.kz.practice.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.kz.practice.util.FillUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 14:31 2020/1/7
 * @ Description：
 * @ Modified By：KatieZ
 * @Version: V
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestUtil {

    @Test
    public void testFillNum(){
//        log.info(FillUtil.fillNumByNum(32,6,6));
//        log.info(FillUtil.fillNumByString(32,6));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String time = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println(time);
    }

    @Test
    public void numberFormat(){
        double f = 111231.5545;
        BigDecimal decimal = new BigDecimal(f);
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("--------------------"+df.format(decimal));
    }

    @Test
    public void add(){
        int i = 7;
        i++;
        System.out.println("i----"+i);
        int j = 7;
        ++j;
        System.out.println("j---------"+j);
    }

    @Test
    public void test(){
        Integer num = 1;
        String str = "ZY";
        List<String> codes = new ArrayList<>();
        codes.add("ZY");
        codes.add("XN000010");
        codes.add("XN000009");
        codes.add("XN000008");
        codes.add("XN000007");
        codes.add("XN000006");
        codes.add("XN000005");
        String maxCode = str.replace("XN","");
        try {
            num = Integer.parseInt(maxCode);
        } catch (NumberFormatException e){
            // 使用正则表达式匹配
            int nums = 6;
            String pattern = "XN\\d{"+nums+"}";
            codes = codes.stream().filter(s-> Pattern.matches(pattern,s)).collect(Collectors.toList());
            log.info("############"+Arrays.toString(codes.toArray()));
        }
    }

    @Test
    public void testBigDecimal(){
        BigDecimal count = new BigDecimal(1);
        log.info("------------"+count.compareTo(BigDecimal.ZERO));
    }

    @Test
    public void testSplit(){
        String menuIds = "1,2,,3,,5,6,7";
        List<Long> newMenuIds = Stream.of(menuIds.split(",")).filter(s -> !StringUtils.isEmpty(s)).map(Long::valueOf).collect(Collectors.toList());
        System.out.println("-------------"+Arrays.toString(newMenuIds.toArray()));
    }
}
