package com.example.talendar.login;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.example.talendar.R;
import com.example.talendar.data.user.UserRemoteDataSource;

public class RegisterOrLoginActivity extends AppCompatActivity {

    private static final String TAG = "RegisterOrLoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: 成功跳转");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        Log.d(TAG, "onCreate: 加载activity布局成功");

        // 加载模块的fragment
        RegisterOrLoginFragment registerOrLoginFragment = RegisterOrLoginFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.lt_for_registerOrLogin_replace, registerOrLoginFragment).commit();

        // 初始化模块的presenter
        new RegisterOrLoginPresenter(new UserRemoteDataSource(), registerOrLoginFragment);
    }

}
