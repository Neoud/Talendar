package com.example.talendar.data.user;

import android.util.Log;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static android.content.ContentValues.TAG;

public class UserRemoteDataSource implements UserDataSource{

    @Override
    public void registerOrLoginIn(String username, String password, RegisterOrLoginInCallBack callBack) {
        Log.d(TAG, "registerOrLoginIn: 校验是否注册过");
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                Log.d(TAG, "done: 返回校验结果");
                if (e == null) {
                    if (list.size() == 0) {
                        Log.d(TAG, "done: 未注册，开始注册并登录");
                        register(username, password, callBack);
                    } else {
                        Log.d(TAG, "done: 已经注册，开始登录");
                        login(username, password, callBack);
                    }
                } else {
                    Log.d(TAG, "done: 登录异常：" + e.getMessage());
                    callBack.onDataNotAvailable(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getUserInfo(String objectId, GetUserInfoCallBack callBack) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", objectId);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    User user = list.get(0);
                    if (user != null) {
                        callBack.onUserInfoGot(user);
                    } else {
                        Log.d(TAG, "done: 查询成功，未查到该用户信息");
                    }
                } else {
                    Log.d(TAG, "done: 获取用户信息错误：" + e.getMessage());
                    callBack.onDataNotAvailable(e.getMessage());
                }
            }
        });
    }

    @Override
    public void register(String username, String password, RegisterOrLoginInCallBack callBack) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFansNumber(0);
        user.setFollowNumber(0);
        user.setLevel(0);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 注册成功，开始登录");
                    login(username, password, callBack);
                } else {
                    Log.d(TAG, "done: 注册异常：" + e.getMessage());
                    callBack.onDataNotAvailable(e.getMessage());
                }
            }
        });
    }

    @Override
    public void login(String username, String password, RegisterOrLoginInCallBack callBack) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    String userObjectId = user.getObjectId();
                    if (userObjectId != null) {
                        Log.d(TAG, "done: 成功登录，开始返回用户信息页面");
                        callBack.onUserRegisterOrLoginIn(userObjectId);
                    } else {
                        Log.d(TAG, "done: 登录成功但是没有返回user对象" + user.toString());
                        callBack.onDataNotAvailable("登录成功但是没有返回user对象");
                    }
                } else {
                    Log.d(TAG, "done: 登录失败");
                    callBack.onDataNotAvailable(e.getMessage());
                }
            }
        });
    }
}
