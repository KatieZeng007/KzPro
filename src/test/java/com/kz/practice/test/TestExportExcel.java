package com.kz.practice.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.kz.practice.excel.drop.DropDownSetField;
import com.kz.practice.excel.drop.entity.ProductModel;
import com.kz.practice.excel.drop.handler.ProductCellWriteHandler;
import com.kz.practice.excel.util.ResoveDropAnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 测试导出下拉数据集
 * @Author KatieZ
 * @Date Created in 16:14  16:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestExportExcel {

    @Test
    public void export() throws IOException {
        File file = new File("D:/产品导入模板.xlsx");
        // 文件不存在即创建 存在即返回false
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        // 获取改类声明的所有字段
        Field[] fields = ProductModel.class.getDeclaredFields();
        // 响应字段对应的下拉集合
        Map<Integer, String[]> map = new HashMap<>();
        Field field = null;
        // 循环判断哪些字段有下拉数据集，并获取
        for(int i =0;i<fields.length;i++){
            field = fields[i];
            // 解析注解信息
            DropDownSetField dropDownSetField = field.getAnnotation(DropDownSetField.class);
            if(null != dropDownSetField){
                String[] sources = ResoveDropAnnotationUtil.resove(dropDownSetField);
                if(null != sources && sources.length > 0){
                    map.put(i,sources);
                }
            }
        }
        ExcelWriter excelWriter = EasyExcel.write(fileOutputStream,ProductModel.class)
                .registerWriteHandler(new ProductCellWriteHandler(map)).build();
        WriteSheet sheet = EasyExcel.writerSheet(0,"产品模板").build();
        excelWriter.write(null,sheet);
        sheet = EasyExcel.writerSheet(1,"单位说明").build();
        excelWriter.write(null,sheet);
        excelWriter.finish();
    }

}
