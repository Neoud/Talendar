package com.example.talendar.data.tale;

import android.util.Log;

import com.example.talendar.data.bean.Creation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.content.ContentValues.TAG;

public class TaleRemoteDataSource implements TaleDataSource{

    @Override
    public List<Tale> getTaleByObjectId(String objectId) {
        final List<Tale>[] taleList = new List[]{null};
        BmobQuery<Tale> query = new BmobQuery<>();
        query.addWhereEqualTo("author", objectId);
        query.findObjects(new FindListener<Tale>() {
            @Override
            public void done(List<Tale> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 获取故事列表成功，故事个数：" + list.size());
                    taleList[0] = list;
                } else {
                    Log.d(TAG, "done: 获取故事列表失败！");
                    taleList[0] = null;
                }
            }
        });
        Log.d(TAG, "getTaleByObjectId: " + taleList[0].size());
        return taleList[0];
    }
}
