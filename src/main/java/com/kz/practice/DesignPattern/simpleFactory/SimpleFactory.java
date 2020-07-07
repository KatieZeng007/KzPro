package com.kz.practice.DesignPattern.simpleFactory;

import com.kz.practice.DesignPattern.simpleFactory.star.WangyiboStar;
import com.kz.practice.DesignPattern.simpleFactory.star.XiaozhanStar;

/**
 * @Description 简单工厂模式
 * @Author KatieZ
 * @Date Created in 10:54  10:54
 */
public class SimpleFactory {

    public static Star createStar(String name){
        if(name.equals("xiaozhan")){
            return new XiaozhanStar();
        }else if(name.equals("wangyibo")){
            return new WangyiboStar();
        }else{
            return null;
        }
    }
}
