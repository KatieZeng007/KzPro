package com.kz.practice.excel.drop.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.kz.practice.excel.drop.DropDownSetField;
import com.kz.practice.excel.drop.impl.DropDownSetImpl;
import lombok.Data;

/**
 * @Description 导出Excel模板实体
 * @Author KatieZ
 * @Date Created in 16:09  16:09
 */
@Data
public class ProductModel {

    @ExcelProperty(value = "商品名称")
    private String name;

    @ExcelProperty(value = "商品类型")
    @DropDownSetField(source = {"易制毒","易制爆"})
    private String type;

    @ExcelProperty(value = "单位")
    @DropDownSetField(sourceClass = DropDownSetImpl.class)
    private String unit;

}
