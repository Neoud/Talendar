package com.example.talendar.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.talendar.R;
import com.example.talendar.data.user.UserRemoteDataSource;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;
import java.util.zip.Inflater;

import cn.bmob.v3.http.I;

public class RegisterOrLoginActivity extends AppCompatActivity {
    private EditText ETusername, ETpassword;
    private Button registerOrLogin, forgetPassword;
    private UserRemoteDataSource mUserDataSource;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_register_login);
        ETusername = findViewById(R.id.editTextUsername);
        ETpassword = findViewById(R.id.editTextPassword);
        registerOrLogin = findViewById(R.id.btnRegisterOrLogin);
        forgetPassword = findViewById(R.id.btnForgetPassword);

        registerOrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = ETusername.toString();
                String pd = ETpassword.toString();
                if (checkUsernameForm(un) && checkPasswordForm(pd)) {
                    Intent dataIntent = new Intent();
                    dataIntent.putExtra("username", un);
                    dataIntent.putExtra("password", pd);
                    setResult(RESULT_OK, dataIntent);
                    finish();
                }
            }
        });
    }

    private boolean checkUsernameForm(String username) {
        if ((username != null) && (!username.isEmpty())) {
            if (Pattern.matches("^1[3-9]\\d{9}$", username)) {
                return true;
            } else if (Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", username)) {
                return true;
            } else {
                Snackbar.make(ETusername, "手机号或邮箱格式不正确！", Snackbar.LENGTH_LONG).show();
                return false;
            }
        } else {
            Snackbar.make(ETusername, "请输入手机号或邮箱！", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean checkPasswordForm(String password) {
        if ((password != null) && (!password.isEmpty())) {
            if (password.length() >= 8 && password.length() <= 14) {
                return true;
            } else {
                Snackbar.make(ETpassword, "请输入8到14位密码！", Snackbar.LENGTH_LONG).show();
                return false;
            }
        } else {
            Snackbar.make(ETpassword, "请输入密码！", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

}
