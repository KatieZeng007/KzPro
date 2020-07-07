package com.kz.practice.DesignPattern.abstractFactory.factory;

import com.kz.practice.DesignPattern.abstractFactory.button.Button;
import com.kz.practice.DesignPattern.abstractFactory.button.WindowsButton;
import com.kz.practice.DesignPattern.abstractFactory.checkbox.CheckBox;
import com.kz.practice.DesignPattern.abstractFactory.checkbox.WindowsCheckbox;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:28  11:28
 */
public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new WindowsCheckbox();
    }
}
