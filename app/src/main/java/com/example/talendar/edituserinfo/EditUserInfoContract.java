package com.example.talendar.edituserinfo;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;

public interface EditUserInfoContract {

    interface Presenter extends BasePresenter {
        void saveAge(String age, String objectId);
        void saveArea(String area, String objectId);
        void saveQuotes(String quotes, String objectId);
        void saveSchool(String school, String objectId);
        void saveSex(String sex, String objectId);
        void saveNick(String nick, String objectId);
        void saveDesc(String desc, String objectId);
    }

    interface View extends BaseView<Presenter> {
         void init(String age, String area, String quotes, String school, String sex, String nick, String desc);
         void setObjectId(String objectId);
    }
}
