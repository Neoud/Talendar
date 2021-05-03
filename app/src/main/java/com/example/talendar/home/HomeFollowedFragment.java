package com.example.talendar.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.talendar.MyApplication;
import com.example.talendar.R;
import com.example.talendar.adapter.CreationHomeAdapter;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.showcreationdetail.ShowCreationDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFollowedFragment extends Fragment implements HomeContract.View {
    private HomeContract.Presenter mPresenter;

    private List<CreationHome> mCreationHomeList = new ArrayList<>();
    private CreationHomeAdapter mCreationHomeAdapter;

    public HomeFollowedFragment() {}

    public static HomeFollowedFragment newInstance() {
        return new HomeFollowedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (MyApplication.userObjectId == null) {
            return inflater.inflate(R.layout.common_not_login, null);
        } else {
            View view = inflater.inflate(R.layout.fragment_home_followed_creation, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_home_followed);
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            mCreationHomeAdapter = new CreationHomeAdapter(mCreationHomeList, new CreationHomeAdapter.OnItemClickedListener() {
                @Override
                public void onClick(CreationHome creationHome) {
                    Intent intent = new Intent(getActivity(), ShowCreationDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("creationHome", creationHome);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            mCreationHomeAdapter.setmPresenter((HomePresenter)mPresenter);
            recyclerView.setAdapter(mCreationHomeAdapter);
            mPresenter.start();
            return view;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (view.getId()) {
            case R.layout.fragment_home_followed_creation:
                mPresenter.start();
                break;
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void setCreationList(List<CreationHome> creationHomeList) {
        mCreationHomeList.addAll(creationHomeList);
        mCreationHomeAdapter.notifyDataSetChanged();
    }
}
