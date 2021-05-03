package com.example.talendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talendar.R;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.home.HomePresenter;
import com.example.talendar.talesolitare.TaleSolitairePresenter;
import com.shehuan.niv.NiceImageView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CreationTSAdapter extends RecyclerView.Adapter<CreationTSAdapter.ViewHolder>{
    private Context mContext;
    private List<CreationHome> mCreationHomeList;
    private CreationTSAdapter mCreationTSAdapter;
    private TaleSolitairePresenter mTaleSolitairePresenter;
    private OnItemClickedListener mListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        NiceImageView NIVProfile;
        TextView nickname, date, title, content, commentNum, fansNum;
        public ViewHolder(View view) {
            super((view));
            cardView = view.findViewById(R.id.cardView_followed_home);
            NIVProfile = view.findViewById(R.id.NIV_home_profile);
            nickname = view.findViewById(R.id.text_home_nickname);
            date = view.findViewById(R.id.text_home_date);
            title = view.findViewById(R.id.text_home_title);
            content = view.findViewById(R.id.text_home_content);
            commentNum = view.findViewById(R.id.text_home_comment_num);
            fansNum = view.findViewById(R.id.text_home_likes_num);
        }
    }

    public interface OnItemClickedListener {
        void onClick(CreationHome creationHome);
    }

    public CreationTSAdapter(List<CreationHome> creationHomeList, OnItemClickedListener listener) {
        mCreationHomeList = creationHomeList;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_followed_item, parent, false);
        mCreationTSAdapter = this;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CreationHome creationHome = mCreationHomeList.get(position);
        Log.d(TAG, "onBindViewHolder: now position is " + position);
//        holder.NIVProfile.setImageBitmap(creationHome.getProfile());
        holder.nickname.setText(creationHome.getNickname());
        holder.date.setText(creationHome.getDate());
        holder.title.setText(creationHome.getTitle());
        holder.content.setText(creationHome.getContent());
        holder.commentNum.setText(creationHome.getCommentNum()+"");
        holder.fansNum.setText(creationHome.getFansNum()+"");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(creationHome);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCreationHomeList.size();
    }

    public void setmPresenter(TaleSolitairePresenter taleSolitairePresenter) {
        mTaleSolitairePresenter = taleSolitairePresenter;
    }

}
