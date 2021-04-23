package com.example.talendar.data.talesolitaire;

import android.util.Log;

import com.example.talendar.data.tale.Tale;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.content.ContentValues.TAG;

public class TaleSolitaireRemoteDataSource implements TaleSolitaireDataSource{

    @Override
    public void getTaleSolitaireByObjectId(String objectId, GetTaleSolitaireCallBack callBack) {
        Log.d(TAG, "getTaleSolitaireByObjectId: 开始获取接龙故事列表");
        BmobQuery<TaleSolitaire> query = new BmobQuery<>();
        query.addWhereEqualTo("author", objectId);
        query.findObjects(new FindListener<TaleSolitaire>() {
            @Override
            public void done(List<TaleSolitaire> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 获取接龙故事列表成功，故事个数：" + list.size());
                    callBack.onTaleSolitaireGot(list);
                } else {
                    Log.d(TAG, "done: 获取接龙故事列表失败！");
                    callBack.onDataNotAvailable("获取接龙故事失败" + e.toString());
                }
            }
        });

    }

    @Override
    public void getTSByTSObjectIds(List<String> taleSolitaireObjectIds, GetTSByTSObjectIdsCallBack callBack) {
        if (!(taleSolitaireObjectIds == null)) {
            Log.d(TAG, "getTSByTSObjectIds: 开始获取接龙故事列表by ts ids");
            BmobQuery<TaleSolitaire> bmobQuery = new BmobQuery<>();
            bmobQuery.addWhereContainedIn("objectId", taleSolitaireObjectIds);
            bmobQuery.findObjects(new FindListener<TaleSolitaire>() {
                @Override
                public void done(List<TaleSolitaire> list, BmobException e) {
                    if (e == null) {
                        Log.d(TAG, "done: 查询TS by TS Object Ids 成功" + list.size());
                        callBack.onTSGotByTSObjectIds(list);
                    } else {
                        Log.d(TAG, "done: 查询TS by TS Object Ids 失败" + e.toString());
                        callBack.onDataNotAvailable(e.toString());
                    }
                }
            });
        }

    }

    @Override
    public void saveTS(TaleSolitaire ts) {
        ts.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 创建tale成功");
                } else {
                    Log.d(TAG, "done: 创建tale失败");
                }
            }
        });
    }

    @Override
    public void deleteTS(String objectId) {
        TaleSolitaire taleSolitaire = new TaleSolitaire();
        taleSolitaire.delete(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 删除ts成功");
                } else {
                    Log.d(TAG, "done: 删除ts失败" + e.toString());
                }
            }
        });
    }
}
