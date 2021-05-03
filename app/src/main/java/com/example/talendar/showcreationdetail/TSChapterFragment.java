package com.example.talendar.showcreationdetail;

import android.content.Intent;
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
import com.example.talendar.adapter.ChapterAdapter;
import com.example.talendar.data.bean.Comments;
import com.example.talendar.data.bean.CreationHome;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TSChapterFragment extends Fragment implements ShowCreationDetailContract.TSView {
    private ShowCreationDetailContract.TSPresenter mPresenter;
    private ChapterAdapter lastChapterAdapter, nextChapterAdapter;
    private List<CreationHome> lastChapter = new ArrayList<>();
    private List<CreationHome> nextChapters = new ArrayList<>();

    public TSChapterFragment(ShowCreationDetailContract.TSPresenter presenter) {
        mPresenter = presenter;
    }

    public static TSChapterFragment newInstance(ShowCreationDetailContract.TSPresenter presenter) {
        return new TSChapterFragment(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ts_chapter, null);
        RecyclerView rvLastChapter = view.findViewById(R.id.recyclerView_ts_last_chapter);
        RecyclerView rvNextChapters = view.findViewById(R.id.recyclerView_ts_next_chapters);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvLastChapter.setLayoutManager(manager);
        rvNextChapters.setLayoutManager(manager2);
        lastChapterAdapter = new ChapterAdapter(lastChapter);
        nextChapterAdapter = new ChapterAdapter(nextChapters);
        rvLastChapter.setAdapter(lastChapterAdapter);
        rvNextChapters.setAdapter(nextChapterAdapter);
        mPresenter.getChapters(mPresenter.getCreationId());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void setText(String viewName, String text) {

    }

    @Override
    public void setPresenter(ShowCreationDetailContract.TSPresenter presenter) {

    }

    @Override
    public void setCommentsList(List<Comments> comments) {

    }

    @Override
    public void setLastChapter(List<CreationHome> lastChapter) {
        Log.d(TAG, "setLastChapter: 更新last chapter list" + lastChapter.size());
        this.lastChapter.addAll(lastChapter);
        lastChapterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setNextChapter(List<CreationHome> nextChapter) {
        Log.d(TAG, "setNextChapter: 更新next chapter list" + nextChapter.size());
        this.nextChapters.addAll(nextChapter);
        nextChapterAdapter.notifyDataSetChanged();
    }
}
