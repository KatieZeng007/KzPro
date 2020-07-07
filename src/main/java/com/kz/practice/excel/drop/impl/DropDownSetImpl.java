package com.kz.practice.excel.drop.impl;

import com.kz.practice.excel.drop.DropDownSetInterface;

/**
 * @Description 动态获取下拉数据集
 * @Author KatieZ
 * @Date Created in 16:07  16:07
 */
public class DropDownSetImpl implements DropDownSetInterface {
    @Override
    public String[] getSource() {
        return new String[]{"g","kg","t","ml","l","米","千米"};
    }
}
