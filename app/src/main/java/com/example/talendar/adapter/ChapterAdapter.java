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
import com.shehuan.niv.NiceImageView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    private Context mContext;
    private List<CreationHome> mCreationHomeList;
    private ChapterAdapter mChapterAdapter;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        NiceImageView NIVProfile;
        TextView nickname, date, title, content, commentNum, fansNum;
        public ViewHolder(View view) {
            super((view));
            cardView = view.findViewById(R.id.cardView_chapter);
            NIVProfile = view.findViewById(R.id.NIV_chapter_profile);
            nickname = view.findViewById(R.id.text_chapter_nickname);
            date = view.findViewById(R.id.text_chapter_date);
            title = view.findViewById(R.id.text_chapter_title);
            content = view.findViewById(R.id.text_chapter_content);
        }
    }

    public ChapterAdapter(List<CreationHome> creationHomeList) {
        mCreationHomeList = creationHomeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.chapter_item, parent, false);
        mChapterAdapter = this;
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
    }

    @Override
    public int getItemCount() {
        return mCreationHomeList.size();
    }


}
