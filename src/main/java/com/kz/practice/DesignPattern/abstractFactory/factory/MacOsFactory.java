package com.kz.practice.DesignPattern.abstractFactory.factory;

import com.kz.practice.DesignPattern.abstractFactory.button.Button;
import com.kz.practice.DesignPattern.abstractFactory.button.MacOsButton;
import com.kz.practice.DesignPattern.abstractFactory.checkbox.CheckBox;
import com.kz.practice.DesignPattern.abstractFactory.checkbox.MacOsCheckbox;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:27  11:27
 */
public class MacOsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacOsButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new MacOsCheckbox();
    }
}
