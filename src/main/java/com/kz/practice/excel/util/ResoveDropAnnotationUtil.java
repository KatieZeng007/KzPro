package com.kz.practice.excel.util;

import com.kz.practice.excel.drop.DropDownSetField;
import com.kz.practice.excel.drop.DropDownSetInterface;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description 解析下拉数据集的注解
 * @Author KatieZ
 * @Date Created in 16:27  16:27
 */
public class ResoveDropAnnotationUtil {


    public static String[] resove(DropDownSetField dropDownSetField){
        if(!Optional.ofNullable(dropDownSetField).isPresent()){
            return null;
        }

        // 获取固定下拉信息
        String[] source = dropDownSetField.source();
        if(null != source && source.length > 0){
            return source;
        }

        // 获取动态的下拉数据
        Class<? extends DropDownSetInterface>[] classes = dropDownSetField.sourceClass();
        if(null != classes && classes.length > 0){
            try {
                DropDownSetInterface dropDownSetInterface = Arrays.stream(classes).findFirst().get().newInstance();
                String[] dynamicSource = dropDownSetInterface.getSource();
                if(null != dynamicSource && dynamicSource.length > 0){
                    return dynamicSource;
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
