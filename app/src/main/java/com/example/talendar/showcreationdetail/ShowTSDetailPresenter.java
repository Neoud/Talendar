package com.example.talendar.showcreationdetail;

import android.util.Log;

import com.example.talendar.data.bean.Comments;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.comment.Comment;
import com.example.talendar.data.comment.CommentDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaire;
import com.example.talendar.data.talesolitaire.TaleSolitaireDataSource;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ShowTSDetailPresenter implements ShowCreationDetailContract.TSPresenter, CommentDataSource.GetCommentListCallBack, TaleSolitaireDataSource.GetCreationCallBack{
    private ShowCreationDetailContract.TSView mTSView, mTSCommentView, mTSChapterView;
    private TaleSolitaireDataSource mTSDataSource;
    private CommentDataSource mCommentDataSource;
    private CreationHome mCreationHome;

    public ShowTSDetailPresenter(ShowCreationDetailContract.TSView tsView, TaleSolitaireDataSource taleSolitaireDataSource, CommentDataSource commentDataSource, CreationHome creationHome) {
        mTSView = tsView;
        mTSDataSource = taleSolitaireDataSource;
        mCommentDataSource = commentDataSource;
        mCreationHome = creationHome;

        mTSView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getComments(String creationId) {
        mCommentDataSource.getCommentList(creationId, 1, this);
    }

    @Override
    public void onCommentListGot(List<Comment> commentList) {
        Log.d(TAG, "onCommentListGot: 加载ts测试comment");
        List<Comments> comments = new ArrayList<>();
        comments.add(new Comments("测试2号", "评论1","2021-04-29 11:32", 12));
        // 拼接数据
        mTSCommentView.setCommentsList(comments);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public String getCreationId() {
        return mCreationHome.getCreationId();
    }

    @Override
    public void getChapters(String creationId) {
        mTSDataSource.getCreation(creationId, this);
    }

    @Override
    public void onCreationGot(List<TaleSolitaire> taleSolitaireList) {
        List<CreationHome> lastChapter = new ArrayList<>();
        List<CreationHome> nextChapter = new ArrayList<>();
        lastChapter.add(new CreationHome("test4", "2021-4-29 10:23" , "title", "creation content", 1,1,1));
        nextChapter.add(new CreationHome("test5", "2021-4-29 10:23" , "title1", "creation content", 1,1,1));
        nextChapter.add(new CreationHome("test4", "2021-4-29 10:23" , "title2", "creation content", 1,1,1));
        nextChapter.add(new CreationHome("test4", "2021-4-29 10:23" , "title3", "creation content", 1,1,1));
        // 逻辑处理
        mTSChapterView.setLastChapter(lastChapter);
        mTSChapterView.setNextChapter(nextChapter);
    }

    @Override
    public void onDataNotAvailable(String message) {

    }

    @Override
    public void setCommentAndChapterView(ShowCreationDetailContract.TSView commentView, ShowCreationDetailContract.TSView chapterView) {
        mTSCommentView = commentView;
        mTSChapterView = chapterView;
    }
}
