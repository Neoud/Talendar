package com.example.talendar.showcreation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.talendar.R;
import com.example.talendar.adapter.CreationAdapter;
import com.example.talendar.adapter.FollowedCreationAdapter;
import com.example.talendar.data.bean.Creation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

import static android.content.ContentValues.TAG;

public class MyFollowedCreationFragment extends Fragment implements ShowCreationContract.View2 {
    private ShowCreationContract.Presenter mPresenter;

    private List<Creation> mFollowedCreationList = new ArrayList<>();
    private FollowedCreationAdapter mFollowedCreationAdapter;

    public MyFollowedCreationFragment() {}

    public static MyFollowedCreationFragment newInstance() {
        return new MyFollowedCreationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BmobUser.isLogin()) {
            View view = inflater.inflate(R.layout.fragment_my_followed_creation, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_my_followed_creation);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            mFollowedCreationAdapter = new FollowedCreationAdapter(mFollowedCreationList);
            recyclerView.setAdapter(mFollowedCreationAdapter);
            return view;
        } else {
            return inflater.inflate(R.layout.common_not_login, null);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadFollowedCreation();
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

    @Override
    public void setCreationList(List<Creation> creationList) {
        mFollowedCreationList.addAll(creationList);
        Log.d(TAG, "setCreationList: 目前需加载总故事数为" + mFollowedCreationList.size());
        mFollowedCreationAdapter.notifyDataSetChanged();
    }
}
