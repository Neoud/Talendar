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
    void saveAge(String age, String objectId);
    void saveArea(String area, String objectId);
    void saveQuotes(String quotes, String objectId);
    void saveSchool(String school, String objectId);
    void saveSex(String sex, String objectId);
    void saveNick(String nick, String objectId);
    void saveDesc(String desc, String objectId);
}
