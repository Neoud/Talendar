package com.example.talendar.edituserinfo;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.talendar.R;
import com.example.talendar.data.user.UserRemoteDataSource;

public class EditUserInfoActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        init();
    }

    private void init() {
        String objectId = getIntent().getExtras().getString("objectId");
        EditUserInfoFragment editUserInfoFragment = EditUserInfoFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeLayout_editUserInfo, editUserInfoFragment).commit();
        new EditUserInfoPresenter(new UserRemoteDataSource(), editUserInfoFragment, objectId);
    }
}
