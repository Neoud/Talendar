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
import com.example.talendar.data.bean.Creation;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FollowedCreationAdapter extends RecyclerView.Adapter<FollowedCreationAdapter.ViewHolder>{
    private Context mContext;
    private List<Creation> mFollowedCreationList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, content, date, tag, type, author;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            title = view.findViewById(R.id.text_my_creation_title);
            content = view.findViewById(R.id.text_my_creation_content);
            date = view.findViewById(R.id.text_my_creation_date);
            tag = view.findViewById(R.id.text_my_creation_tag);
            type = view.findViewById(R.id.text_creation_type);
            author = view.findViewById(R.id.text_creation_author);
        }
    }

    public FollowedCreationAdapter(List<Creation> followedCreationList) {
        mFollowedCreationList = followedCreationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.creation_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: no position is " + position);
        Creation creation = mFollowedCreationList.get(position);
        holder.title.setText(creation.getTitle());
        holder.content.setText(creation.getContent());
        holder.date.setText(creation.getDate());
        holder.type.setText(creation.getType());
        holder.author.setText(creation.getAuthorName());
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tags = creation.getTags();
        for (int i = 0 ; i < tags.size() ; i ++) {
            stringBuilder.append(tags.get(i) + " ");
        }
        if (stringBuilder.length() == 0) {
            holder.tag.setText("");
        } else {
            holder.tag.setText(stringBuilder.toString());
        }
    }

    @Override
    public int getItemCount() {
        return mFollowedCreationList.size();
    }
}
