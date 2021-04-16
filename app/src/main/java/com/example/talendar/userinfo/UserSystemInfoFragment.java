package com.example.talendar.userinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.CallLog;
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

import com.example.talendar.MainActivity;
import com.example.talendar.R;
import com.shehuan.niv.NiceImageView;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class UserSystemInfoFragment extends Fragment implements UserSystemInfoContract.View {

    private View view;
    private UserSystemInfoContract.Presenter mPresenter;

    private final int REQUEST_CODE_LOGIN = 1;
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
    public void setLoginBtnVisibility(String visibility) {

    }

    @Override
    public void showProfile(Bitmap profile) {
        niVProfile.setImageBitmap(profile);
    }

    @Override
    public void showUserInfo(String nick, String desc, String followNum, String fansNum, String level, String sex, String age, String area, String school, String quotes) {
        tvNick.setText(nick);
        tvDesc.setText(desc);
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
                String username = data.getExtras().getString("username");
                String password = data.getExtras().getString("password");
                mPresenter.loginIn(username, password);
        }
    }

    @Override
    public void showException(String exception) {
        Toast.makeText(getContext(), exception, Toast.LENGTH_LONG).show();
    }
}
