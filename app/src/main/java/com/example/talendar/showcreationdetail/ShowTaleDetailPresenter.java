package com.example.talendar.showcreationdetail;

import android.util.Log;

import com.example.talendar.data.bean.Comments;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.comment.Comment;
import com.example.talendar.data.comment.CommentDataSource;
import com.example.talendar.data.tale.TaleDataSource;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ShowTaleDetailPresenter implements ShowCreationDetailContract.TalePresenter, CommentDataSource.GetCommentListCallBack{
    private ShowCreationDetailContract.TaleView mTaleView;
    private TaleDataSource mTaleDataSource;
    private CommentDataSource mCommentDataSource;
    private CreationHome mCreationHome;

    public ShowTaleDetailPresenter(ShowCreationDetailContract.TaleView taleView, TaleDataSource taleDataSource, CommentDataSource commentDataSource, CreationHome creationHome) {
        mTaleView = taleView;
        mTaleDataSource = taleDataSource;
        mCommentDataSource = commentDataSource;
        mCreationHome = creationHome;

        mTaleView.setPresenter(this);
    }

    @Override
    public void start() {
        mTaleView.setText("nickName", mCreationHome.getNickname());
        mTaleView.setText("date", mCreationHome.getDate());
        mTaleView.setText("title", mCreationHome.getTitle());
        mTaleView.setText("content", mCreationHome.getContent());
        mTaleView.setText("commentNum", mCreationHome.getCommentNum()+"");
        mTaleView.setText("fansNum", mCreationHome.getFansNum()+"");
        Log.d(TAG, "start: 加载评论" + mCreationHome.getCreationId());
        mCommentDataSource.getCommentList(mCreationHome.getCreationId(),1, this);
    }

    @Override
    public void onCommentListGot(List<Comment> commentList) {
        List<Comments> comments = new ArrayList<>();
        comments.add(new Comments("测试3号", "评论3","2021-04-29 11:32", 11));
        mTaleView.setCommentsList(comments);
    }

    @Override
    public void onDataNotAvailable() {

    }


}
