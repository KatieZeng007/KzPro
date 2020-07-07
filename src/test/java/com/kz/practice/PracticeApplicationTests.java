package com.kz.practice;

import com.google.common.collect.Lists;
import com.kz.practice.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @SpringBootTest
class PracticeApplicationTests {

	@Test
	void contextLoads() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.toString();
	}

	/**
	 * new Integer[]{2} 结果存放容器
	 * (a, x) -> a[0] += x 结果如何添加到容器
	 * 多个容器的聚合策略
	 */
	@Test
	void collect(){
		final Integer[] integers = Lists.newArrayList(1, 2, 3, 4, 5)
				.stream()
				.collect(() -> new Integer[]{2}, (a, x) -> a[0] += x, (a1, a2) -> a1[0] += a2[0]);
	}

}
