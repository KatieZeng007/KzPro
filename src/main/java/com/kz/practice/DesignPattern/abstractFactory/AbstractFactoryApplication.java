package com.kz.practice.DesignPattern.abstractFactory;

import com.kz.practice.DesignPattern.abstractFactory.factory.GUIFactory;
import com.kz.practice.DesignPattern.abstractFactory.button.Button;
import com.kz.practice.DesignPattern.abstractFactory.checkbox.CheckBox;

/**
 * @Description 抽象工厂模式
 * @Author KatieZ
 * @Date Created in 11:21  11:21
 */
public class AbstractFactoryApplication {

    private Button button;
    private CheckBox checkBox;

    public AbstractFactoryApplication(GUIFactory guiFactory){
        button = guiFactory.createButton();
        checkBox = guiFactory.createCheckbox();
    }

    public void paint(){
        button.paint();
        checkBox.paint();
    }

}
