package com.example.talendar.showcreation;

import android.util.Log;

import com.example.talendar.data.bean.Creation;
import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaire;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ShowCreationPresenter implements ShowCreationContract.Presenter {
    private TaleRemoteDataSource mTaleDataSource;
    private TaleSolitaireRemoteDataSource mTaleSolitaireDataSource;
    private ShowCreationContract.View mShowCreationView;
    private ShowCreationContract.View2 mShowFollowedCreationView;
    private String mObjectId;

    public ShowCreationPresenter(TaleRemoteDataSource taleRemoteDataSource, TaleSolitaireRemoteDataSource taleSolitaireRemoteDataSource, ShowCreationContract.View view, ShowCreationContract.View2 view2, String objectId) {
        mTaleDataSource = taleRemoteDataSource;
        mTaleSolitaireDataSource = taleSolitaireRemoteDataSource;
        mShowCreationView = view;
        mShowFollowedCreationView = view2;
        mObjectId = objectId;

        mShowCreationView.setPresenter(this);
        mShowFollowedCreationView.setPresenter(this);
    }

    @Override
    public void start() {
        List<Tale> taleList = mTaleDataSource.getTaleByObjectId(mObjectId);
        List<TaleSolitaire> taleSolitaireList = mTaleSolitaireDataSource.getTaleSolitaireByObjectId(mObjectId);
        List<Creation> creationList = getCreation(taleList, taleSolitaireList);
        mShowCreationView.setCreationList(creationList);
    }

    @Override
    public List<Creation> getCreation(List<Tale> taleList, List<TaleSolitaire> taleSolitaireList) {
        Log.d(TAG, "getCreation: 开始拼接数据");
        List<Creation> creationList = new ArrayList<>();
        int size = taleList.size();
        int size2 = taleList.size();
        Log.d(TAG, "getCreation: 故事数量：" + size + "接龙故事数量：" + size2);
        for (int i = 0 ; i < size ; i ++) {
            Tale tale = taleList.get(i);
            Creation creation = new Creation(tale.getTitle(), tale.getContent(), tale.getCreatedAt(), tale.getTags());
            creationList.add(creation);
        }
        for (int i = 0 ; i <size2 ; i ++) {
            TaleSolitaire taleSolitaire = taleSolitaireList.get(i);
            Creation creation = new Creation(taleSolitaire.getTitle(), taleSolitaire.getContent(), taleSolitaire.getCreatedAt(), taleSolitaire.getTags());
            creationList.add(creation);
        }
        Log.d(TAG, "getCreation: 故事拼接结束！");
        return creationList;
    }
}
