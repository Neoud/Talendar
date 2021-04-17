package com.example.talendar.login;

import android.content.Intent;
import android.util.Log;

import com.example.talendar.data.user.UserDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;

import java.util.regex.Pattern;

import cn.bmob.v3.exception.BmobException;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class RegisterOrLoginPresenter implements RegisterOrLoginContract.Presenter, UserDataSource.RegisterOrLoginInCallBack{
    private UserDataSource mUserDataSource;
    private RegisterOrLoginContract.View mLoginView;

    public RegisterOrLoginPresenter(UserRemoteDataSource userDataSource, RegisterOrLoginContract.View loginView) {
        mUserDataSource = userDataSource;
        mLoginView = loginView;
        loginView.setPresenter(this);
        Log.d(TAG, "RegisterOrLoginPresenter: RegisterOrLoginPresenter初始化成功");
    }

    @Override
    public void start() {

    }

    public boolean checkUsernameForm(String username) {
        if ((username != null) && (!username.isEmpty())) {
            if (Pattern.matches("^1[3-9]\\d{9}$", username)) {
                return true;
            } else if (Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", username)) {
                return true;
            } else {
                mLoginView.showSnackBar(1, "手机号或邮箱格式不正确！");
                return false;
            }
        } else {
            mLoginView.showSnackBar(1, "请输入手机号或邮箱！");
            return false;
        }
    }

    public boolean checkPasswordForm(String password) {
        if ((password != null) && (!password.isEmpty())) {
            if (password.length() >= 8 && password.length() <= 14) {
                return true;
            } else {
                mLoginView.showSnackBar(2, "请输入8-14位的密码");
                return false;
            }
        } else {
            mLoginView.showSnackBar(2, "请输入密码");
            return false;
        }
    }

    @Override
    public void registerOrLoginIn(String username, String password) {
        mUserDataSource.registerOrLoginIn(username, password, this);
    }

    @Override
    public void onUserRegisterOrLoginIn(String objectId) {
        Log.d(TAG, "onUserLoginIn: 登录成功: " + objectId);
        mLoginView.handleRegisterOrLoginInInfo(objectId);
    }

    @Override
    public void onDataNotAvailable(String message) {
        mLoginView.showToast(message);
    }
}
