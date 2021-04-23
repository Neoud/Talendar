package com.example.talendar;

import android.app.Application;

import com.example.talendar.data.user.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MyApplication extends Application {

    public static String userObjectId;
    public static String nickName;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "21b6dbfafac1426bc755deced5b59984");
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            String name = user.getNickname();
            userObjectId = user.getObjectId();
            if (name.equals("") || (name == null)) {
                nickName = user.getUsername();
            } else {
                nickName = user.getNickname();
            }
        } else {
            userObjectId = null;
            nickName = null;
        }
    }
}
