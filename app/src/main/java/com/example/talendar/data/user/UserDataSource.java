package com.example.talendar.data.user;

import android.graphics.Bitmap;

import cn.bmob.v3.exception.BmobException;

public interface UserDataSource {
    interface loginInCallBack {
        void onUserLoginIn(String objectId);
        void onDataNotAvailable(BmobException e);
    }
    interface getUserInfoCallBack {
        void onUserInfoGot(User user);
        void onDataNotAvailable(BmobException e);
    }
    void loginIn(String username, String password);
    void getUserInfo(String objectId);
}
