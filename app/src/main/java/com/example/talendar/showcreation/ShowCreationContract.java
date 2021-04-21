package com.example.talendar.showcreation;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;
import com.example.talendar.data.bean.Creation;
import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.talesolitaire.TaleSolitaire;

import java.util.List;

public interface ShowCreationContract {

    interface Presenter extends BasePresenter {
        List<Creation> getCreation(List<Tale> taleList, List<TaleSolitaire> taleSolitaireList);
    }

    interface View extends BaseView<Presenter> {
        void setCreationList(List<Creation> creationList);
    }

    interface View2 extends BaseView<Presenter> {

    }
}
