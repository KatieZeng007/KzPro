package com.kz.practice.DesignPattern.strategy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 信用卡
 * @Author KatieZ
 * @Date Created in 13:30  13:30
 */
@Data
public class CreditCard implements Serializable {

    private int amount;
    private String number;
    private String date;
    private String cvv;

    public CreditCard(String number, String date, String cvv){
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }
}
