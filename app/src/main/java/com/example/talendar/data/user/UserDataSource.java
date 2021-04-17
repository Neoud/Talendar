package com.example.talendar.data.user;

import android.graphics.Bitmap;

import cn.bmob.v3.exception.BmobException;

public interface UserDataSource {
    interface RegisterOrLoginInCallBack {
        void onUserRegisterOrLoginIn(String objectId);
        void onDataNotAvailable(String message);
    }
    interface GetUserInfoCallBack {
        void onUserInfoGot(User user);
        void onDataNotAvailable(String message);
    }
    void registerOrLoginIn(String username, String password, RegisterOrLoginInCallBack callBack);
    void getUserInfo(String objectId, GetUserInfoCallBack callBack);
    void register(String username, String password, RegisterOrLoginInCallBack callBack);
    void login(String username, String password, RegisterOrLoginInCallBack callBack);
}
