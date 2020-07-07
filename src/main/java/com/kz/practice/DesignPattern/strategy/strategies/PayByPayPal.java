package com.kz.practice.DesignPattern.strategy.strategies;

import com.kz.practice.DesignPattern.strategy.PayStrategy;
import com.sun.org.apache.regexp.internal.RE;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 使用PayPal支付策略
 * @Author KatieZ
 * @Date Created in 13:15  13:15
 */
public class PayByPayPal implements PayStrategy {

    private static final Map<String,String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

    static {
        DATA_BASE.put("KZ","KZ@163.com");
        DATA_BASE.put("Tom","Tom.163.com");
    }

    @Override
    public boolean pay(int paymentAmount) {
        if(signedIn){
            System.out.println("Paying "+paymentAmount+" using PayPal");
            return true;
        }else{
            return false;
        }
    }

    /**
     * 收集客户数据
     */
    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn){
                System.out.print("Enter the user's email:");
                email = READER.readLine();
                System.out.print("Enter the password:");
                password = READER.readLine();
                if(verify()){
                    System.out.print("Data verification has been successful.");
                }else{
                    System.out.print("Wrong email or password!");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean verify(){
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    private void setSignedIn(boolean signedIn){
        this.signedIn = signedIn;
    }
}
