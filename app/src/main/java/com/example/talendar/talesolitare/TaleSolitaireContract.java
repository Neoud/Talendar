package com.example.talendar.talesolitare;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;
import com.example.talendar.data.bean.CreationHome;

import java.util.List;

public interface TaleSolitaireContract {

    interface Presenter extends BasePresenter {
        void initTaleSolitaireShow(int tag);
    }

    interface View extends BaseView<Presenter> {
        void setCreationList(List<CreationHome>creationHomeList);
    }
}
