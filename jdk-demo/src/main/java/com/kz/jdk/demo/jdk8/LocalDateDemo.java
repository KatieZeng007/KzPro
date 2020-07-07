package com.kz.jdk.demo.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 17:29  17:29
 */
@Slf4j
public class LocalDateDemo {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        // ------------------------------------获取值-----------------------------------
        // 年份
        int year = localDate.getYear();
        // 月份
        int month = localDate.getMonth().getValue();
        // 日
        int day = localDate.getDayOfMonth();
        // 星期
        int dayOfWeek = localDate.getDayOfWeek().getValue();
        // 获取月份的天数
        int dayNums = localDate.lengthOfMonth();
        // 是否闰年
        boolean flag = localDate.isLeapYear();
        // ------------------------------------更新值-------------------------------------
        // 实例化
        LocalDate date = LocalDate.of(2013,1,4);
        log.info(date.toString());
        // 修改年
        LocalDate newYear = date.withYear(2014);
        log.info(newYear.toString());
        // 修改月
        LocalDate newMonth = date.with(ChronoField.MONTH_OF_YEAR,2);
        log.info(newMonth.toString());
        // 加一周
        LocalDate newWeek = date.plusWeeks(1);
        log.info(newWeek.toString());
        // 减少6年
        LocalDate newSubYear = date.minusYears(6);
        log.info(newSubYear.toString());
        // 加6个月
        LocalDate newAddMonth = date.plus(6, ChronoUnit.MONTHS);
        log.info(newAddMonth.toString());
    }
}
