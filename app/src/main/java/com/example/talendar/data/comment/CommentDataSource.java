package com.example.talendar.data.comment;

import java.util.List;

public interface CommentDataSource {
    interface GetCommentListCallBack {
        void onCommentListGot(List<Comment> commentList);
        void onDataNotAvailable();
    }

    void getCommentList(String objectId, int type, GetCommentListCallBack callBack);
}
