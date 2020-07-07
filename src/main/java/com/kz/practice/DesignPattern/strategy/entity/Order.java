package com.kz.practice.DesignPattern.strategy.entity;

import com.kz.practice.DesignPattern.strategy.PayStrategy;
import lombok.Data;

/**
 * @Description 订单
 * 不知道用户使用的具体付款方式（策略）
 * 使用通用策略界面来委托收集付款数据
 * @Author KatieZ
 * @Date Created in 13:32  13:32
 */
@Data
public class Order {

    private int totalCost = 0;

    private boolean isClosed = false;

    public void processOrder(PayStrategy payStrategy){
        payStrategy.collectPaymentDetails();
        // Here we could collect and store payment data from the strategy.
    }

    public void setTotalCost(int cost){
        this.totalCost += cost;
    }

    public void setClosed() {
        this.isClosed = true;
    }
}
