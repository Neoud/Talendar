package com.example.talendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.talendar.userinfo.UserInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建各个模块的碎片
        UserInfoFragment userInfoFragment = UserInfoFragment.newInstance();

        // 注册底部导航栏监听事件
        BottomNavigationView bnView = findViewById(R.id.bottom_nav_view);
        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_nav_content, userInfoFragment).commit();
                        break;
                    case R.id.nav_tale_solitaire:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_nav_content, view).commit();
                        break;
                    case R.id.nav_post:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_nav_content, userInfoFragment).commit();
                        break;
                    case R.id.nav_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_nav_content, userInfoFragment).commit();
                        break;
                }
                return true;
            }
        });

        // 初始化数据
        init();
    }

    /**
     * @description 初始化数据
     * @author Pontus
     * @date 2021/4/15 17:20
     */
    private void init() {

    }
}