package com.example.talendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talendar.R;
import com.example.talendar.data.bean.Creation;
import java.util.List;

public class CreationAdapter extends RecyclerView.Adapter<CreationAdapter.ViewHolder> {
    private Context mContext;
    private List<Creation> mCreationList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, content, date, tag;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            title = view.findViewById(R.id.text_my_creation_title);
            content = view.findViewById(R.id.text_my_creation_content);
            date = view.findViewById(R.id.text_my_creation_date);
            tag = view.findViewById(R.id.text_my_creation_tag);
        }
    }

    public CreationAdapter(List<Creation> creationList) {
        mCreationList = creationList;
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
        Creation creation = mCreationList.get(position);
        holder.title.setText(creation.getTitle());
        holder.content.setText(creation.getContent());
        holder.date.setText(creation.getDate());
        holder.tag.setText(creation.getTag());
    }

    @Override
    public int getItemCount() {
        return mCreationList.size();
    }
}
