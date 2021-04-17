package com.example.talendar.userinfo;

import android.graphics.Bitmap;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;

import com.example.talendar.data.user.User;
import com.example.talendar.data.user.UserDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;

import static android.content.ContentValues.TAG;


public class UserSystemInfoPresenter implements UserSystemInfoContract.Presenter, UserDataSource.GetUserInfoCallBack{

    private UserDataSource mUserSource;
    private UserSystemInfoContract.View mUserView;

    public UserSystemInfoPresenter(UserRemoteDataSource userSource, UserSystemInfoContract.View userView) {
        mUserSource = userSource;
        mUserView = userView;

        Log.d(TAG, "UserSystemInfoPresenter: Set presenter for user and system info fragment");
        mUserView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getUserInfo(String objectId) {
        Log.d(TAG, "getUserInfo: 开始获取用户信息");
        mUserSource.getUserInfo(objectId, this);
        Log.d(TAG, "getUserInfo: 成功获取用户信息");
    }


    @Override
    public void onUserInfoGot(User user) {
        Log.d(TAG, "onUserInfoGot: 开始加载用户信息");
        mUserView.setLoginBtnVisibility(View.GONE);
        Bitmap profile = getProfileByBmobFile(user.getProfile());
        mUserView.showProfile(profile);
        String nickname, desc, followNum, fansNum, level, sex, age, area, school, quotes;
        nickname = checkUserInfo(user.getNickname());
        desc = checkUserInfo(user.getDescription());
        followNum = checkUserInfo(user.getFollowNumber());
        fansNum = checkUserInfo(user.getFansNumber());
        level = checkUserInfo(user.getLevel());
        sex = checkUserInfo(user.getSex());
        age = checkUserInfo(user.getAge());
        area = checkUserInfo(user.getArea());
        school = checkUserInfo(user.getSchool());
        quotes = checkUserInfo(user.getQuotes());
        mUserView.showUserInfo(nickname, desc, followNum, fansNum, level, sex, age, area, school, quotes);
        Log.d(TAG, "onUserInfoGot: 用户信息加载完成");
    }

    @Override
    public void onDataNotAvailable(String message) {
        mUserView.showToast(message);
    }

    @Override
    public Bitmap getProfileByBmobFile(BmobFile profile) {
        return null;
    }

    @Override
    public String checkUserInfo(String info) {
        if (info != null && !info.isEmpty()) {
            return info;
        } else {
            return "-";
        }
    }

    @Override
    public String checkUserInfo(int info) {
        return info + "";
    }
}
