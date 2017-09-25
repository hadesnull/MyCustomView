package com.cai.yi.myapplication0.factory;

/**
 * Created by qf on 2017/9/25.
 */

public class FactoryBuild {


    public static final int type_1 = 1;
    public static final int type_2 = 2;

    public static IFactory getEnstance(int type) {

        switch (type) {

            case type_1:
                return new FactoryOne();

            case type_2:
                return new FactoryTwo();

            default:
                return null;

        }
    }
}
