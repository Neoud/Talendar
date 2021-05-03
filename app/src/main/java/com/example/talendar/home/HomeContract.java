package com.example.talendar.home;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;
import com.example.talendar.data.bean.CreationHome;

import java.util.List;

public interface HomeContract {

    interface Presenter extends BasePresenter {
        void initRecommendFragment(int tagCode);
    }


    interface View extends BaseView<Presenter> {
        void setCreationList(List<CreationHome> creationHomeList);
    }

    interface View2 extends BaseView<Presenter> {
        void setCreationList(List<CreationHome> creationHomeList);
    }
}
