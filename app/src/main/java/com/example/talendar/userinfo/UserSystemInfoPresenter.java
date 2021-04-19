package com.example.talendar.userinfo;

import android.graphics.Bitmap;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;

import com.example.talendar.data.user.User;
import com.example.talendar.data.user.UserDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;
import cn.bmob.v3.datatype.BmobFile;

import static android.content.ContentValues.TAG;


public class UserSystemInfoPresenter implements UserSystemInfoContract.Presenter, UserDataSource.GetUserInfoCallBack{

    private UserRemoteDataSource mUserSource;
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

    /**
     * @description 通过用户id获取用户信息
     * @param objectId 用户id
     * @author Pontus
     * @date 2021/4/17 23:08
     */
    @Override
    public void getUserInfo(String objectId) {
        Log.d(TAG, "getUserInfo: 开始获取用户信息");
        mUserSource.getUserInfo(objectId, this);
        Log.d(TAG, "getUserInfo: 成功获取用户信息");
    }


    /**
     * @description 获取到用户信息后，调用fragment对象展示数据
     * @param user 用户bean，包含所要展示的用户信息
     * @author Pontus
     * @date 2021/4/17 23:09
     */
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

    /**
     * @description 拿到数据库中对应的头像信息返回头像的Bitmap数据
     * @param profile 数据库中对应的头像信息
     * @return 如果用户上传过头像则返回用户头像的Bitmap数据，否则返回null
     * @author Pontus
     * @date 2021/4/17 23:10
     */
    @Override
    public Bitmap getProfileByBmobFile(BmobFile profile) {
        return null;
    }

    /**
     * @description 拿到用户信息后，格式化数据，返回最终需要展示的数据
     * @param info 数据库中的原始数据
     * @return 最终需要展示的数据
     * @author Pontus
     * @date 2021/4/17 23:13
     */
    @Override
    public String checkUserInfo(String info) {
        if (info != null && !info.isEmpty()) {
            return info;
        } else {
            return "-";
        }
    }

    /**
     * @description 拿到用户信息后，格式化数据，返回最终需要展示的数据
     * @param info 数据库中的原始数据
     * @return 最终需要展示的数据
     * @author Pontus
     * @date 2021/4/17 23:14
     */
    @Override
    public String checkUserInfo(int info) {
        return info + "";
    }
}
