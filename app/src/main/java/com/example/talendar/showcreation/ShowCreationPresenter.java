package com.example.talendar.showcreation;

import android.util.Log;

import com.example.talendar.data.bean.Creation;
import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.tale.TaleDataSource;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaire;
import com.example.talendar.data.talesolitaire.TaleSolitaireDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;
import com.example.talendar.data.user.User;
import com.example.talendar.data.user.UserDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static android.content.ContentValues.TAG;

public class ShowCreationPresenter implements ShowCreationContract.Presenter, TaleSolitaireDataSource.GetTaleSolitaireCallBack, TaleDataSource.GetTaleCallBack, TaleDataSource.GetTaleByTaleObjectIdsCallBack, TaleSolitaireDataSource.GetTSByTSObjectIdsCallBack, UserDataSource.GetUserInfoCallBack {
    private TaleRemoteDataSource mTaleDataSource;
    private TaleSolitaireRemoteDataSource mTaleSolitaireDataSource;
    private ShowCreationContract.View mShowCreationView;
    private ShowCreationContract.View2 mShowFollowedCreationView;
    private String mObjectId;
    private Timer mTimer = new Timer();


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
        mTaleDataSource.getTaleByObjectId(mObjectId, this);
        mTaleSolitaireDataSource.getTaleSolitaireByObjectId(mObjectId, this);
    }


    @Override
    public void onDataNotAvailable(String message) {
        mShowCreationView.showToast(message);
    }

    @Override
    public void onTaleGot(List<Tale> taleList) {
        List<Creation> creationList = new ArrayList<>();
        for (int i = 0; i < taleList.size(); i ++) {
            Tale tale = taleList.get(i);
            Log.d(TAG, "onTaleGot: 获取到用户信息，开始更新故事列表" + tale.getObjectId());
            Creation creation = new Creation(tale.getObjectId(), tale.getTitle(), tale.getContent(), tale.getCreatedAt(), "故事", tale.getAuthorName(), tale.getTags());
            creationList.add(creation);
        }
        mShowCreationView.setCreationList(creationList);
    }

    @Override
    public void onTaleSolitaireGot(List<TaleSolitaire> taleSolitaireList) {
        List<Creation> creationList = new ArrayList<>();
        for (int i = 0; i < taleSolitaireList.size(); i ++) {
            TaleSolitaire taleSolitaire = taleSolitaireList.get(i);
            Creation creation = new Creation(taleSolitaire.getObjectId(), taleSolitaire.getTitle(), taleSolitaire.getContent(), taleSolitaire.getCreatedAt(), "接龙故事", taleSolitaire.getAuthorName(), taleSolitaire.getTags());
            creationList.add(creation);
        }
        mShowCreationView.setCreationList(creationList);
    }

    @Override
    public void loadFollowedCreation() {
        Log.d(TAG, "loadFollowedCreation: 开始获取用户关注的故事的id");
        UserRemoteDataSource userRemoteDataSource = new UserRemoteDataSource();
        userRemoteDataSource.getUserInfo(mObjectId, this);
    }

    @Override
    public void onTaleGotByTaleObjectIds(List<Tale> taleList) {
        List<Creation> creationList = new ArrayList<>();
        for (int i = 0; i < taleList.size(); i ++) {
            Tale tale = taleList.get(i);
            Creation creation = new Creation(tale.getObjectId(), tale.getTitle(), tale.getContent(), tale.getCreatedAt(), "故事", tale.getAuthorName(), tale.getTags());
            Log.d(TAG, "onTSGotByTSObjectIds: 添加creation进list" + creation.getCreationId());
            creationList.add(creation);
        }
        mShowFollowedCreationView.setCreationList(creationList);
    }

    @Override
    public void onTSGotByTSObjectIds(List<TaleSolitaire> taleSolitaireList) {
        List<Creation> creationList = new ArrayList<>();
        for (int i = 0; i < taleSolitaireList.size(); i ++) {
            TaleSolitaire taleSolitaire = taleSolitaireList.get(i);
            Creation creation = new Creation(taleSolitaire.getObjectId(), taleSolitaire.getTitle(), taleSolitaire.getContent(), taleSolitaire.getCreatedAt(), "接龙故事", taleSolitaire.getAuthorName(), taleSolitaire.getTags());
            Log.d(TAG, "onTSGotByTSObjectIds: 添加creation进list" + creation.getCreationId());
            creationList.add(creation);
        }
        mShowFollowedCreationView.setCreationList(creationList);
    }

    @Override
    public void onUserInfoGot(User user) {
        mTaleDataSource.getTaleByTaleObjectIds(user.getTales(), this);
        mTaleSolitaireDataSource.getTSByTSObjectIds(user.getTaleSolitaires(), this);
    }

    @Override
    public void deleteCreation(Creation creation) {
        switch (creation.getType()) {
            case "故事":
                Log.d(TAG, "deleteCreation: 删除tale" + creation.getCreationId());
                mTaleDataSource.deleteTale(creation.getCreationId());
                break;
            case "接龙故事":
                Log.d(TAG, "deleteCreation: 删除ts" + creation.getCreationId());
                mTaleSolitaireDataSource.deleteTS(creation.getCreationId());
                break;
        }
    }
}
