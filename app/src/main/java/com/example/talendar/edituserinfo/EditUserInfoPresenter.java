package com.example.talendar.edituserinfo;

import android.util.Log;

import com.example.talendar.data.user.User;
import com.example.talendar.data.user.UserDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;

import static android.content.ContentValues.TAG;

public class EditUserInfoPresenter implements EditUserInfoContract.Presenter{
    private UserRemoteDataSource mUserDataSource;
    private EditUserInfoContract.View mEditUserInfoView;
    private String mObjectId;

    public EditUserInfoPresenter(UserRemoteDataSource userRemoteDataSource, EditUserInfoContract.View editUserInfoView, String objectId) {
        mObjectId = objectId;
        mUserDataSource = userRemoteDataSource;
        mEditUserInfoView = editUserInfoView;
        Log.d(TAG, "EditUserInfoPresenter: Edit user info: user id is " + objectId);
        editUserInfoView.setObjectId(objectId);
        editUserInfoView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveDesc(String desc, String objectId) {
        mUserDataSource.saveDesc(desc, objectId);
    }

    @Override
    public void saveNick(String nick, String objectId) {
        mUserDataSource.saveNick(nick, objectId);
    }

    @Override
    public void saveAge(String age, String objectId) {
        mUserDataSource.saveAge(age, objectId);
    }

    @Override
    public void saveArea(String area, String objectId) {
        mUserDataSource.saveArea(area, objectId);
    }

    @Override
    public void saveQuotes(String quotes, String objectId) {
        mUserDataSource.saveQuotes(quotes, objectId);
    }

    @Override
    public void saveSchool(String school, String objectId) {
        mUserDataSource.saveSchool(school, objectId);
    }

    @Override
    public void saveSex(String sex, String objectId) {
        mUserDataSource.saveSex(sex, objectId);
    }

}
