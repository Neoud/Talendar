package com.example.talendar.showcreationdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.talendar.R;
import com.example.talendar.adapter.CommentAdapter;
import com.example.talendar.data.bean.Comments;
import com.example.talendar.data.bean.CreationHome;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TSCommentsFragment extends Fragment implements ShowCreationDetailContract.TSView {
    private ShowCreationDetailContract.TSPresenter mPresenter;
    private List<Comments> mComments = new ArrayList<>();
    private CommentAdapter mCommentAdapter;
    private String mCreationId;

    public TSCommentsFragment(ShowCreationDetailContract.TSPresenter presenter, String creationId) {
        mPresenter = presenter;
        mCreationId = creationId;
    }

    public static TSCommentsFragment newInstance(ShowCreationDetailContract.TSPresenter presenter, String creationId) {
        return new TSCommentsFragment(presenter, creationId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: 加载comment fragment");
        View view = inflater.inflate(R.layout.fragment_ts_comments, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_ts_comment);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mCommentAdapter = new CommentAdapter(mComments);
        mCommentAdapter.setmTSPresenter((ShowCreationDetailContract.TSPresenter)mPresenter);
        recyclerView.setAdapter(mCommentAdapter);
        mPresenter.getComments(mCreationId);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(ShowCreationDetailContract.TSPresenter presenter) {

    }

    @Override
    public void setText(String viewName, String text) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void setCommentsList(List<Comments> comments) {
        mComments.addAll(comments);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void setNextChapter(List<CreationHome> nextChapter) {

    }

    @Override
    public void setLastChapter(List<CreationHome> lastChapter) {

    }
}
