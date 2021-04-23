package com.example.talendar.data.talesolitaire;

import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.tale.TaleDataSource;

import java.util.List;

public interface TaleSolitaireDataSource {

    interface GetTaleSolitaireCallBack {
        void onTaleSolitaireGot(List<TaleSolitaire> taleSolitaireList);
        void onDataNotAvailable(String message);
    }

    interface GetTSByTSObjectIdsCallBack {
        void onTSGotByTSObjectIds(List<TaleSolitaire> taleSolitaireList);
        void onDataNotAvailable(String message);
    }

    void getTaleSolitaireByObjectId(String objectId, GetTaleSolitaireCallBack callBack);
    void getTSByTSObjectIds(List<String> taleSolitaireObjectIds, TaleSolitaireDataSource.GetTSByTSObjectIdsCallBack callBack);
    void saveTS(TaleSolitaire ts);
    void deleteTS(String objectId);
}
