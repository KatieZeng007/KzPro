package com.kz.practice.functional.inter;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:11  11:11
 */
@FunctionalInterface
public interface InterfaceThree extends InterfaceOne,InterfaceTwo {

    @Override
    default int sum(int x, int y) {
        return InterfaceOne.super.sum(x, y);
    }
}
