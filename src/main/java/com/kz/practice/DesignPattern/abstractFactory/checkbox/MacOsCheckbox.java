package com.kz.practice.DesignPattern.abstractFactory.checkbox;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:25  11:25
 */
public class MacOsCheckbox implements CheckBox{
    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }
}
