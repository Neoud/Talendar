package com.example.talendar.userinfo;

import android.graphics.Bitmap;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;

public interface UserSystemInfoContract {

    interface Presenter extends BasePresenter {
        void getUserInfo(String objectId);
        Bitmap getProfileByBmobFile(BmobFile profile);
        String checkUserInfo(String info);
        String checkUserInfo(int info);
    }

    interface View extends BaseView<Presenter> {
        void setLoginBtnVisibility(int visibility);
        void showProfile(Bitmap profile);
        void showUserInfo(String nick, String desc, String followNum, String fansNum, String level, String sex, String age, String area, String school, String quotes);
        void showToast(String message);
    }
}
