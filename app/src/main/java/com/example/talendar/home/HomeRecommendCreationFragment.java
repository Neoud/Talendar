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

import com.example.talendar.R;
import com.example.talendar.adapter.CreationRecommendAdapter;
import com.example.talendar.adapter.CreationHomeAdapter;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.showcreationdetail.ShowCreationDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeRecommendCreationFragment extends Fragment implements HomeContract.View2 {
    private HomeContract.Presenter mPresenter;
    private CreationRecommendAdapter mCreationRecommendAdapter;
    private List<CreationHome> mCreationHomeList =new ArrayList<>();
    private int mTag;

    public HomeRecommendCreationFragment(int tag) {
        mTag = tag;
    }

    public static HomeRecommendCreationFragment newInstance(int tag) {
        return new HomeRecommendCreationFragment(tag);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_recommend_creation_creation, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_home_recommend);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mCreationRecommendAdapter = new CreationRecommendAdapter(mCreationHomeList, new CreationRecommendAdapter.OnItemClickedListener() {
            @Override
            public void onClick(CreationHome creationHome) {
                Intent intent = new Intent(getActivity(), ShowCreationDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("creationHome", creationHome);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mCreationRecommendAdapter.setRecommendPresenter((HomeRecommendPresenter)mPresenter);
        recyclerView.setAdapter(mCreationRecommendAdapter);
        mPresenter.initRecommendFragment(mTag);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void setCreationList(List<CreationHome> creationHomeList) {
        mCreationHomeList.addAll(creationHomeList);
        mCreationRecommendAdapter.notifyDataSetChanged();
    }
}
