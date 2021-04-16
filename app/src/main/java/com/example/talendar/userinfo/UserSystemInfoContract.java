package com.example.talendar.userinfo;

import android.graphics.Bitmap;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;

public interface UserSystemInfoContract {

    interface Presenter extends BasePresenter {
        void getUserInfo(String objectId);
        void registerOrLoginIn(String username, String password);
        void loginIn(String username, String password);
        Bitmap getProfileByBmobFile(BmobFile profile);
    }

    interface View extends BaseView<Presenter> {
        void setLoginBtnVisibility(String visibility);
        void showProfile(Bitmap profile);
        void showUserInfo(String nick, String desc, String followNum, String fansNum, String level, String sex, String age, String area, String school, String quotes);
        void showException(String exception);
    }
}
