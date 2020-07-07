package com.kz.practice.DesignPattern.abstractFactory.factory;

import com.kz.practice.DesignPattern.abstractFactory.button.Button;
import com.kz.practice.DesignPattern.abstractFactory.checkbox.CheckBox;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:26  11:26
 */
public interface GUIFactory {
    Button createButton();
    CheckBox createCheckbox();
}
