package com.kz.practice.designPattern;

import com.kz.practice.DesignPattern.abstractFactory.AbstractFactoryApplication;
import com.kz.practice.DesignPattern.abstractFactory.factory.GUIFactory;
import com.kz.practice.DesignPattern.abstractFactory.factory.MacOsFactory;
import com.kz.practice.DesignPattern.abstractFactory.factory.WindowsFactory;
import com.kz.practice.DesignPattern.simpleFactory.SimpleFactory;
import com.kz.practice.DesignPattern.simpleFactory.Star;
import com.kz.practice.DesignPattern.singleton.SingleThread.Singleton;
import com.kz.practice.DesignPattern.singleton.SingleThread.SynchronizedSingleton;
import com.kz.practice.DesignPattern.strategy.PayStrategy;
import com.kz.practice.DesignPattern.strategy.entity.Order;
import com.kz.practice.DesignPattern.strategy.strategies.PayByCreditCard;
import com.kz.practice.DesignPattern.strategy.strategies.PayByPayPal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 设计模式测试
 * @Author KatieZ
 * @Date Created in 10:52  10:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DesignPatternTest {

    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    /**
     * 简单工厂模式
     */
    @Test
    public void testSimpleFactory(){
        Star xiaozhanStar = SimpleFactory.createStar("xiaozhan");
        Star wangyiboStar = SimpleFactory.createStar("wangyibo");
        log.info(xiaozhanStar.getStar());
        log.info(wangyiboStar.getStar());
    }

    // ==================== 抽象工厂模式 ========================

    private static AbstractFactoryApplication configureApplication(){
        GUIFactory factory;
        String osName = "win";
        if(osName.equals("os")){
            factory = new MacOsFactory();
        }else{
            factory = new WindowsFactory();
        }
        return new AbstractFactoryApplication(factory);
    }

    /**
     * 抽象工厂模式
     */
    @Test
    public void testAbstractFactory(){
        AbstractFactoryApplication app = configureApplication();
        app.paint();
    }

    @Test
    public void testStrategy() throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }

                // Order object delegates gathering payment data to strategy
                // object, since only strategies know what data they need to
                // process a payment.
                order.processOrder(strategy);

                System.out.print("Pay " + order.getTotalCost() + " units or Continue shopping? P/C: ");
                String proceed = reader.readLine();
                if (proceed.equalsIgnoreCase("P")) {
                    // Finally, strategy handles the payment.
                    if (strategy.pay(order.getTotalCost())) {
                        System.out.println("Payment has been successful.");
                    } else {
                        System.out.println("FAIL! Please, check your data.");
                    }
                    order.setClosed();
                }
            }
        }
    }

    /**
     * 单线程单例模式
     */
    public void testSingleThread(){
        Singleton singleton = Singleton.getInstance("foo");
        Singleton anotherSingleton = Singleton.getInstance("bar");
        // RESULT
        // foo
        // foo
    }

    /**
     * 多线程单例模式
     */
    public void testMutilThread(){
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
        // RESULT
        // foo
        // bar
    }

    static class ThreadFoo implements Runnable{

        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("foo");
        }
    }

    static class ThreadBar implements Runnable{

        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("bar");
        }
    }

    @Test
    public void testSynchornizedSingleton() {
        Thread threadFoo = new Thread(new SynThreadFoo());
        Thread threadBar = new Thread(new SynThreadBar());
        threadFoo.start();
        threadBar.start();
        // RESULT
        // foo
        // bar
    }

    static class SynThreadFoo implements Runnable {
        @Override
        public void run() {
            SynchronizedSingleton singleton = SynchronizedSingleton.getInstance("foo");
            System.out.println(singleton.value);
        }
    }

    static class SynThreadBar implements Runnable {
        @Override
        public void run() {
            SynchronizedSingleton singleton = SynchronizedSingleton.getInstance("bar");
            System.out.println(singleton.value);
        }
    }


}
