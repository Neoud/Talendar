package com.example.talendar.showcreationdetail;

import com.example.talendar.BasePresenter;
import com.example.talendar.BaseView;
import com.example.talendar.data.bean.Comments;
import com.example.talendar.data.bean.CreationHome;

import java.util.List;

public interface ShowCreationDetailContract {
    interface TalePresenter extends BasePresenter {

    }

    interface TSPresenter extends BasePresenter {
        void getComments(String creationId);
        String getCreationId();
        void getChapters(String creationId);
        void setCommentAndChapterView(ShowCreationDetailContract.TSView commentView, ShowCreationDetailContract.TSView chapterView);
    }

    interface TaleView extends BaseView<TalePresenter> {
        void setText(String viewName, String text);
        void setCommentsList(List<Comments> comments);
    }

    interface TSView extends BaseView<TSPresenter> {
        void setText(String viewName, String text);
        void setCommentsList(List<Comments> comments);
        void setLastChapter(List<CreationHome> lastChapter);
        void setNextChapter(List<CreationHome> nextChapter);
    }

}
