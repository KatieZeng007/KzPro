package com.kz.jdk.demo.jdk8;

import com.kz.jdk.demo.entity.Apple;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 流式函数
 * @Author KatieZ
 * @Date Created in 15:10  15:10
 */
@Slf4j
public class StreamDemo {

    public static void main(String[] args) {
        // 初始化集合
        List<Apple> list = Stream.of(new Apple("A",30),new Apple("B",40),new Apple("C",50)).collect(Collectors.toList());
        // 过滤集合
        Stream<Apple> applesStream = list.stream().filter(x -> x.getWeight() > 30);
        // 输出过滤集合
        applesStream.forEach(System.out::println);
        // 构造Stream
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        // 从数组中构造
        Integer[] array = {1,2,3,5};
        Stream<Integer> stream = Arrays.stream(array);
    }

    /**
     * 取最小值
     */
    public void testMin(){
        Integer[] array = {1,2,3,4,5};
        Optional<Integer> min = Stream.of(array).min((x1,x2) -> x1.compareTo(x2));
        log.info(min.get().toString());
    }

    /**
     * 取最大值
     */
    public void testMax(){
        Integer[] array = {1,2,3,4,5};
        Optional<Integer> max = Stream.of(array).max((x1,x2) -> x1.compareTo(x2));
        log.info(max.get().toString());
    }

    // ------------------------- 使用reduce 进行计算 --------------------------------------------

    /**
     * 求和
     */
    public void reduceSum(){
        Integer[] array = {1,2,3,4,5};
        Integer sum = Stream.of(array).reduce(Integer::sum).orElse(0).intValue();
        log.info(sum.toString());
        // 从0 开始计算合计 0+1 1+2 3+3 6+4 10+5
        Integer sumNext = Stream.of(array).reduce(0,(p1,p2) -> p1+p2).intValue();
        log.info(sumNext.toString());
    }

    /**
     * 最大值
     */
    public void reduceMax(){
        Integer[] array = {1,2,3,4,5};
        Integer sum = Stream.of(array).reduce(Integer::max).orElse(0).intValue();
        log.info(sum.toString());
    }

    /**
     * 最小值
     */
    public void reduceMin(){
        Integer[] array = {1,2,3,4,5};
        Integer sum = Stream.of(array).reduce(Integer::min).orElse(0).intValue();
        log.info(sum.toString());
    }


}
