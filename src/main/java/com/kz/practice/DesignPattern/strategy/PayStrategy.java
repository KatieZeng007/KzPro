package com.kz.practice.DesignPattern.strategy;

/**
 * @Description 针对所有策略的接口
 * @Author KatieZ
 * @Date Created in 13:13  13:13
 */
public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}
