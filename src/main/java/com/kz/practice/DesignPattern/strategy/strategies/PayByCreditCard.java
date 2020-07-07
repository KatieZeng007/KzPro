package com.kz.practice.DesignPattern.strategy.strategies;

import com.kz.practice.DesignPattern.strategy.PayStrategy;
import com.kz.practice.DesignPattern.strategy.entity.CreditCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description 使用信用卡支付策略
 * @Author KatieZ
 * @Date Created in 13:29  13:29
 */
public class PayByCreditCard implements PayStrategy {
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Paying " + paymentAmount + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 收集信用卡信息
     */
    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Enter the card number: ");
            String number = READER.readLine();
            System.out.print("Enter the card expiration date 'mm/yy': ");
            String date = READER.readLine();
            System.out.print("Enter the CVV code: ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);
            // Validate credit card number...
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}
