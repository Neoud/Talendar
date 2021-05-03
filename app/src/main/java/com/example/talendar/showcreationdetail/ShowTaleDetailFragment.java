package com.example.talendar.showcreationdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.talendar.R;
import com.example.talendar.adapter.CommentAdapter;
import com.example.talendar.data.bean.Comments;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.List;

public class ShowTaleDetailFragment extends Fragment implements ShowCreationDetailContract.TaleView, View.OnClickListener{
    private ShowCreationDetailContract.TalePresenter mPresenter;
    private TextView nickName, date, title, content, commentNum, fansNum;
    private NiceImageView NIVProfile;
    private List<Comments> mComments = new ArrayList<>();
    private CommentAdapter mCommentAdapter;

    public ShowTaleDetailFragment() {}

    public static ShowTaleDetailFragment newInstance() {
        return new ShowTaleDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_tale_detail, null);
        nickName = view.findViewById(R.id.text_tale_detail_nickname);
        date = view.findViewById(R.id.text_tale_detail_date);
        title = view.findViewById(R.id.text_tale_detail_title);
        content = view.findViewById(R.id.text_tale_detail_content);
        commentNum = view.findViewById(R.id.text_tale_detail_comment_num);
        commentNum.setOnClickListener(this);
        fansNum = view.findViewById(R.id.text_tale_detail_likes_num);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_detail_tale);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mCommentAdapter = new CommentAdapter(mComments);
        mCommentAdapter.setmPresenter((ShowTaleDetailPresenter)mPresenter);
        recyclerView.setAdapter(mCommentAdapter);
        mPresenter.start();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(ShowCreationDetailContract.TalePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void setText(String viewName, String text) {
        switch (viewName) {
            case "nickName":
                nickName.setText(text);
                break;
            case "date":
                date.setText(text);
                break;
            case "title":
                title.setText(text);
                break;
            case "content":
                content.setText(text);
                break;
            case "commentNum":
                commentNum.setText(text);
                break;
            case "fansNum":
                fansNum.setText(text);
                break;
            default:
                break;
        }
    }

    @Override
    public void setCommentsList(List<Comments> comments) {
        mComments.addAll(comments);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_tale_detail_comment_num:
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                commentDialogFragment.show(getFragmentManager(), "CommentDialogFragment");
                break;
        }
    }
}
