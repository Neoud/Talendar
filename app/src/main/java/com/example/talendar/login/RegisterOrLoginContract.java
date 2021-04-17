package com.example.talendar.login;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;

public interface RegisterOrLoginContract {
    interface Presenter extends BasePresenter {
        boolean checkUsernameForm(String username);
        boolean checkPasswordForm(String password);
        void registerOrLoginIn(String username, String password);
    }

    interface View extends BaseView<Presenter> {
        void showSnackBar(int code, String message);
        void showToast(String message);
        void handleRegisterOrLoginInInfo(String objectId);
    }
}
