package com.kz.jdk.demo.jdk8;

import com.kz.jdk.demo.entity.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 17:19  17:19
 */
public class LambdaDemo {

    public static void main(String[] args) {
        List<Apple> list = Stream.of(new Apple("A",20),new Apple("B",30),new Apple("C",40))
                .collect(Collectors.toList());
        // 排序
        Comparator<Apple> comparator = (a,b) -> a.getWeight().compareTo(b.getWeight());
        Collections.sort(list,comparator);
        list.stream().forEach(System.out::print);
    }
}
