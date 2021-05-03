package com.example.talendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.talendar.R;
import com.example.talendar.data.bean.Comments;
import com.example.talendar.showcreationdetail.ShowCreationDetailContract;
import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private Context mContext;
    private List<Comments> mComments;
    private CommentAdapter mCommentAdapter;
    private ShowCreationDetailContract.TalePresenter mPresenter;
    private ShowCreationDetailContract.TSPresenter mTSPresenter;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout commentItem;
        TextView nick, content, date, fansNum;
        public ViewHolder(View view) {
            super(view);
            commentItem = (LinearLayout) view;
            nick = view.findViewById(R.id.text_comment_nickname);
            content = view.findViewById(R.id.text_comment_detail);
            date = view.findViewById(R.id.text_comment_date);
            fansNum = view.findViewById(R.id.text_comment_likes_num);
        }
    }

    public CommentAdapter(List<Comments> comments) {
        mComments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false);
        mCommentAdapter = this;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comments comments = mComments.get(position);
        holder.nick.setText(comments.getNick());
        holder.content.setText(comments.getContent());
        holder.date.setText(comments.getDate());
        holder.fansNum.setText(comments.getFansNum()+"");
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void setmPresenter(ShowCreationDetailContract.TalePresenter presenter) {
        mPresenter = presenter;
    }

    public void setmTSPresenter(ShowCreationDetailContract.TSPresenter tsPresenter) {
        mTSPresenter = tsPresenter;
    }
}
