package com.example.talendar.home;

import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.tale.TaleDataSource;
import com.example.talendar.data.user.UserDataSource;

import java.util.ArrayList;
import java.util.List;

public class HomeRecommendPresenter implements HomeContract.Presenter{
    HomeContract.View2 mView2;
    TaleDataSource mTaleDataSource;
    UserDataSource mUserDataSource;

    public HomeRecommendPresenter(HomeContract.View2 view2, TaleDataSource taleDataSource, UserDataSource userDataSource) {
        mView2 = view2;
        mTaleDataSource = taleDataSource;
        mUserDataSource = userDataSource;

        mView2.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void initRecommendFragment(int tagCode) {
        List<CreationHome> creationHomeList = new ArrayList<>();
        creationHomeList.add(new CreationHome("测试1号", "2021-4-24 15:56:22", "这是一个推荐的日常故事", "这是一个推荐的日常故事的内容", 2, 2, 1));
        mView2.setCreationList(creationHomeList);
    }
}
