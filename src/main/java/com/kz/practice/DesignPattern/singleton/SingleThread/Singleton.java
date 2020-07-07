package com.kz.practice.DesignPattern.singleton.SingleThread;

/**
 * @Description 单线程单例模式
 * @Author KatieZ
 * @Date Created in 13:43  13:43
 */
public class Singleton {

    private static Singleton instance;

    public String value;

    private Singleton(String value){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value){
        if(instance == null){
            instance = new Singleton(value);
        }
        return instance;
    }

}
