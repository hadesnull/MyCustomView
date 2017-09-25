package com.cai.yi.myapplication0.factory;

import android.util.Log;

/**
 * Created by qf on 2017/9/25.
 */

public class FactoryTwo implements IFactory {

    @Override
    public void save() {
        Log.i("IFactory", "FactoryTwo");
    }
}
