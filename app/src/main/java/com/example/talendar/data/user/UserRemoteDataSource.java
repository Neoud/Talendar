package com.example.talendar.data.user;

import android.util.Log;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.content.ContentValues.TAG;

public class UserRemoteDataSource implements UserDataSource{

    public UserRemoteDataSource() {}

    /**
     * @description 注册登录，若用户未注册则先注册再登录，若已注册则直接登录
     * @param username 用户注册的账号
     * @param password 用户注册的密码
     * @param callBack 回调类，处理异常情况
     * @author Pontus
     * @date 2021/4/17 23:19
     */
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

    /**
     * @description 通过用户id获取用户信息
     * @param objectId 用户id
     * @param callBack 获取到用户信息后的回调类
     * @author Pontus
     * @date 2021/4/17 23:21
     */
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

    /**
     * @description 用户注册
     * @param username 用户账号
     * @param password 用户密码
     * @param callBack 注册时的回调函数，成功则传给登录函数，失败则处理失败信息
     * @author Pontus
     * @date 2021/4/17 23:23
     */
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

    /**
     * @description 用户登录
     * @param username 用户账号
     * @param password 用户密码
     * @param callBack 登录时的处理类，登录成功或失败都调用该类处理信息
     * @return
     * @author Pontus
     * @date 2021/4/17 23:25
     */
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

    @Override
    public void saveAge(String age, String objectId) {
        User user= new User();
        user.setAge(age);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新age成功");
                } else {
                    Log.d(TAG, "done: 更新age失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveArea(String area, String objectId) {
        User user = new User();
        user.setArea(area);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新area成功");
                } else {
                    Log.d(TAG, "done: 更新area失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveQuotes(String quotes, String objectId) {
        User user = new User();
        user.setQuotes(quotes);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新quotes成功");
                } else {
                    Log.d(TAG, "done: 更新quotes失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveSchool(String school, String objectId) {
        User user = new User();
        user.setSchool(school);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新school成功");
                } else {
                    Log.d(TAG, "done: 更新school失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveSex(String sex, String objectId) {
        User user = new User();
        user.setSex(sex);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新sex成功");
                } else {
                    Log.d(TAG, "done: 更新sex失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveNick(String nick, String objectId) {
        User user = new User();
        user.setNickname(nick);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新nick成功");
                } else {
                    Log.d(TAG, "done: 更新nick失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveDesc(String desc, String objectId) {
        User user = new User();
        user.setDescription(desc);
        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新desc成功");
                } else {
                    Log.d(TAG, "done: 更新desc失败" + e.toString());
                }
            }
        });
    }
}
