package com.example.talendar.data.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentRemoteDataSource implements CommentDataSource{

    @Override
    public void getCommentList(String objectId, int type, GetCommentListCallBack callBack) {
        List<Comment> comments = new ArrayList<>();
        callBack.onCommentListGot(comments);
    }
}
