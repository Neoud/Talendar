package com.example.talendar.userinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.talendar.R;
import com.example.talendar.login.RegisterOrLoginActivity;
import com.example.talendar.login.RegisterOrLoginFragment;
import com.shehuan.niv.NiceImageView;

import static android.content.ContentValues.TAG;

public class UserSystemInfoFragment extends Fragment implements UserSystemInfoContract.View {
    private final int REQUEST_CODE_LOGIN = 1;

    private UserSystemInfoContract.Presenter mPresenter;
    private View view;

    private Button btnLogin;
    private NiceImageView niVProfile;
    private TextView tvNick, tvDesc, tvFollowNum, tvFansNum, tvLevel, tvSex, tvAge, tvArea, tvSchool, tvQuotes;

    public UserSystemInfoFragment() {}

    public static UserSystemInfoFragment newInstance() {
        return new UserSystemInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_system_info, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        niVProfile = view.findViewById(R.id.profile);
        tvNick = view.findViewById(R.id.text_user_name);
        tvDesc = view.findViewById(R.id.text_user_description);
        tvFollowNum = view.findViewById(R.id.text_follow_number);
        tvFansNum = view.findViewById(R.id.text_fans_number);
        tvLevel = view.findViewById(R.id.text_level);
        tvSex = view.findViewById(R.id.text_sex);
        tvAge = view.findViewById(R.id.text_age);
        tvArea = view.findViewById(R.id.text_area);
        tvSchool = view.findViewById(R.id.text_school);
        tvQuotes = view.findViewById(R.id.text_quotes);
        btnLogin = view.findViewById(R.id.button_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 开始跳转" + getActivity().getLocalClassName());
                Intent loginIntent = new Intent(getActivity(), RegisterOrLoginActivity.class);
                startActivityForResult(loginIntent, REQUEST_CODE_LOGIN);
            }
        });
    }

    @Override
    public void setPresenter(UserSystemInfoContract.Presenter presenter) {
        mPresenter = presenter;
        Log.d(TAG, "setPresenter: set presenter for user and system info fragment done: " + mPresenter.toString());
    }

    @Override
    public void setLoginBtnVisibility(int visibility) {
        btnLogin.setVisibility(visibility);
    }

    @Override
    public void showProfile(Bitmap profile) {
        if (! (profile == null)) {
            niVProfile.setImageBitmap(profile);
        }
    }

    @Override
    public void showUserInfo(String nick, String desc, String followNum, String fansNum, String level, String sex, String age, String area, String school, String quotes) {
        if (nick.equals("-")) {
            tvNick.setText("用户xxx");
        } else {
            tvNick.setText(nick);
        }
        if (!desc.equals("-")){
            tvDesc.setText(desc);
        }
        tvFollowNum.setText(followNum);
        tvFansNum.setText(fansNum);
        tvLevel.setText(level);
        tvSex.setText(sex);
        tvAge.setText(age);
        tvArea.setText(area);
        tvSchool.setText(school);
        tvQuotes.setText(quotes);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                String objectId = data.getExtras().getString("objectId");
                Log.d(TAG, "onActivityResult: 成功返回用户信息页面(" + objectId + ")，并开始获取用户信息");
                mPresenter.getUserInfo(objectId);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
