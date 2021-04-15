package com.example.talendar;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "21b6dbfafac1426bc755deced5b59984");
    }
}
