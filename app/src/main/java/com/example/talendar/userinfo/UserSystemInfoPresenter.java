package com.example.talendar.userinfo;

import android.graphics.Bitmap;
import android.service.autofill.UserData;
import android.util.Log;

import com.example.talendar.data.user.User;
import com.example.talendar.data.user.UserDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;

import static android.content.ContentValues.TAG;


public class UserSystemInfoPresenter implements UserSystemInfoContract.Presenter, UserDataSource.loginInCallBack, UserDataSource.getUserInfoCallBack {

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

    }

    @Override
    public void registerOrLoginIn(String username, String password) {

    }

    @Override
    public void loginIn(String username, String password) {
        mUserSource.loginIn(username, password);
    }

    @Override
    public void onUserLoginIn(String objectId) {
        mUserSource.getUserInfo(objectId);
    }

    @Override
    public void onUserInfoGot(User user) {
        Bitmap profile = getProfileByBmobFile(user.getProfile());
        mUserView.showProfile(profile);
        mUserView.showUserInfo(user.getNickname(), user.getDescription(), user.getFollowNumber()+"", user.getFansNumber()+"", user.getLevel()+"", user.getSex(), user.getAge(), user.getArea(), user.getSchool(), user.getQuotes());
    }

    @Override
    public void onDataNotAvailable(BmobException e) {
        mUserView.showException(e.toString());
    }

    @Override
    public Bitmap getProfileByBmobFile(BmobFile profile) {
        return null;
    }
}
