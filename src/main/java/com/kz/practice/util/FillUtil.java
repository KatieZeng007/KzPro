package com.kz.practice.util;

import java.text.NumberFormat;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 14:10 2020/1/7
 * @ Description：填充工具
 * @ Modified By：KatieZ
 * @Version: V1。0
 */
public class FillUtil {

    /**
     * 左位补0的操作
     * @param num 操作数据
     * @param min 整数部分最小位数
     * @param max 整数部分最大位数
     * @return
     */
    public static String fillNumByNum(int num,int min,int max){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        // 设置以组的格式显示数据 000.00
        numberFormat.setGroupingUsed(false);
        // 设置数字的整数部分允许的最小位数
        numberFormat.setMinimumIntegerDigits(min);
        // 设置数字的整数部分允许的最大位数
        numberFormat.setMaximumIntegerDigits(max);
        return numberFormat.format(num);
    }

    /**
     * 左位补0的操作
     * @param num 操作数据
     * @param offset 补充数据位数
     * @return
     */
    public static String fillNumByString(int num,int offset){
        return String.format("%0"+offset+"d",num);
    }
}
