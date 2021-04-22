package com.example.talendar.data.tale;

import com.example.talendar.data.bean.Creation;

import java.util.ArrayList;
import java.util.List;

public interface TaleDataSource {

    interface GetTaleCallBack {
        void onTaleGot(List<Tale> taleList);
        void onDataNotAvailable(String message);
    }

    interface GetTaleByTaleObjectIdsCallBack {
        void onTaleGotByTaleObjectIds(List<Tale> taleList);
        void onDataNotAvailable(String message);
    }


    void getTaleByObjectId(String objectId, GetTaleCallBack callBack);
    void getTaleByTaleObjectIds(List<String> taleObjectIds, GetTaleByTaleObjectIdsCallBack callBack);
    void saveTaleByObjectId(String objectId, Tale tale);
}
