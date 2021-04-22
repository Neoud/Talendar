package com.example.talendar.data.tale;

import android.util.Log;

import com.example.talendar.data.bean.Creation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static android.content.ContentValues.TAG;

public class TaleRemoteDataSource implements TaleDataSource{

    @Override
    public void getTaleByObjectId(String objectId, GetTaleCallBack callBack) {
        Log.d(TAG, "getTaleByObjectId: 开始获取故事列表");
        BmobQuery<Tale> query = new BmobQuery<>();
        query.addWhereEqualTo("author", objectId);
        query.findObjects(new FindListener<Tale>() {
            @Override
            public void done(List<Tale> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 获取故事列表成功，故事个数：" + list.size());
                    callBack.onTaleGot(list);
                } else {
                    Log.d(TAG, "done: 获取故事列表失败！");
                    callBack.onDataNotAvailable("获取故事列表失败" + e.toString());
                }
            }
        });
    }

    @Override
    public void saveTaleByObjectId(String objectId, Tale tale) {
        tale.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    Log.d(TAG, "done:  创建tale成功" + s.toString());
                } else {
                    Log.d(TAG, "done: 创建tale失败" + e.toString());
                }
            }
        });

    }

    @Override
    public void getTaleByTaleObjectIds(List<String> taleObjectIds, GetTaleByTaleObjectIdsCallBack callBack) {
        if (!(taleObjectIds == null)) {
            Log.d(TAG, "getTaleByTaleObjectIds: 开始获取故事列表by tale id");
            BmobQuery<Tale> bmobQuery = new BmobQuery<>();
            bmobQuery.addWhereContainedIn("objectId", taleObjectIds);
            bmobQuery.findObjects(new FindListener<Tale>() {
                @Override
                public void done(List<Tale> list, BmobException e) {
                    if (e == null) {
                        Log.d(TAG, "done: 查询故事by tale ids 成功" + list.size());
                        callBack.onTaleGotByTaleObjectIds(list);
                    } else {
                        Log.d(TAG, "done: 查询故事by tale ids 失败");
                        callBack.onDataNotAvailable(e.toString());
                    }
                }
            });
        }
    }
}
