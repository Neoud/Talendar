package com.example.talendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;
import com.example.talendar.data.user.User;
import com.example.talendar.data.user.UserRemoteDataSource;
import com.example.talendar.showcreation.MyCreationFragment;
import com.example.talendar.showcreation.MyFollowedCreationFragment;
import com.example.talendar.showcreation.ShowCreationFragment;
import com.example.talendar.showcreation.ShowCreationPresenter;
import com.example.talendar.userinfo.UserSystemInfoFragment;
import com.example.talendar.userinfo.UserSystemInfoPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserSystemInfoFragment USInfoFragment;
    private ShowCreationFragment showCreationFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        USInfoFragment = UserSystemInfoFragment.newInstance();
        showCreationFragment = ShowCreationFragment.newInstance();
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
                    case R.id.nav_creation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_nav_content, showCreationFragment).commit();
                        break;
                    case R.id.nav_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_nav_content, USInfoFragment).commit();
                        Log.d(TAG, "onNavigationItemSelected: init user and system info presenter");
                        new UserSystemInfoPresenter(new UserRemoteDataSource(), USInfoFragment);
                        break;
                }
                return true;
            }
        });
        bnView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });
        init();
    }

    /**
     * @description 初始化数据
     * @author Pontus
     * @date 2021/4/15 17:20
     */
    private void init() {

    }

    /**
     * @description 从其他activity获取到数据后，通过在activity的onActivityResult方法中调用对应fragment的onActivityResult方法来展示数据
     * @author Pontus
     * @date 2021/4/17 23:16
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (USInfoFragment != null) {
            Log.d(TAG, "onActivityResult: 开始调用UserSystemInfoFragment的onActivityResult方法");
            USInfoFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}