package com.example.talendar.showcreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.talendar.R;

import cn.bmob.v3.BmobUser;

public class MyFollowedCreationFragment extends Fragment implements ShowCreationContract.View2 {
    private ShowCreationContract.Presenter mPresenter;

    public MyFollowedCreationFragment() {}

    public static MyFollowedCreationFragment newInstance() {
        return new MyFollowedCreationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BmobUser.isLogin()) {
            return inflater.inflate(R.layout.fragment_my_followed_creation, null);
        } else {
            return inflater.inflate(R.layout.common_not_login, null);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(ShowCreationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {

    }
}
