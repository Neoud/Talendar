package com.example.talendar.data.talesolitaire;

import android.util.Log;

import com.example.talendar.data.tale.Tale;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.content.ContentValues.TAG;

public class TaleSolitaireRemoteDataSource implements TaleSolitaireDataSource{

    @Override
    public List<TaleSolitaire> getTaleSolitaireByObjectId(String objectId) {
        final List<TaleSolitaire>[] taleSolitaireList = new List[]{null};
        BmobQuery<TaleSolitaire> query = new BmobQuery<>();
        query.addWhereEqualTo("author", objectId);
        query.findObjects(new FindListener<TaleSolitaire>() {
            @Override
            public void done(List<TaleSolitaire> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 获取接龙故事列表成功，故事个数：" + list.size());
                    taleSolitaireList[0] = list;
                } else {
                    Log.d(TAG, "done: 获取接龙故事列表失败！");
                    taleSolitaireList[0] = null;
                }
            }
        });
        Log.d(TAG, "getTaleByObjectId: " + taleSolitaireList[0].size());
        return taleSolitaireList[0];
    }
}
