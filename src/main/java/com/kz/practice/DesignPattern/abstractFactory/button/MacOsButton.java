package com.kz.practice.DesignPattern.abstractFactory.button;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:24  11:24
 */
public class MacOsButton implements Button{
    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}
