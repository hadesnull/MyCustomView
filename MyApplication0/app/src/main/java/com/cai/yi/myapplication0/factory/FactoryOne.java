package com.cai.yi.myapplication0.factory;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by qf on 2017/9/25.
 */

public class FactoryOne implements IFactory {

    @Override
    public void save() {
        Log.i("IFactory", "FactoryOne");
    }
}
