package com.example.talendar.edittaleorts;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;
import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.talesolitaire.TaleSolitaire;

public interface EditTaleOrTsContract {

    interface Presenter extends BasePresenter {
        void saveTale(Tale tale);
        void saveTS(TaleSolitaire ts);
    }

    interface View extends BaseView<Presenter> {

    }
}
