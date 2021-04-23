package com.example.talendar.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.talendar.R;
import com.example.talendar.data.bean.Creation;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;
import com.example.talendar.showcreation.ShowCreationContract;
import com.example.talendar.showcreation.ShowCreationPresenter;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CreationAdapter extends RecyclerView.Adapter<CreationAdapter.ViewHolder> {
    private Context mContext;
    private List<Creation> mCreationList;
    private CreationAdapter mAdapter;
    private ShowCreationContract.Presenter mPresenter;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, content, date, tag, type;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            title = view.findViewById(R.id.text_my_creation_title);
            content = view.findViewById(R.id.text_my_creation_content);
            date = view.findViewById(R.id.text_my_creation_date);
            tag = view.findViewById(R.id.text_my_creation_tag);
            type = view.findViewById(R.id.text_creation_type);
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
        mAdapter = this;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Creation creation = mCreationList.get(position);
        Log.d(TAG, "onBindViewHolder: now position is " + position);
        holder.title.setText(creation.getTitle());
        holder.content.setText(creation.getContent());
        holder.date.setText(creation.getDate());
        holder.type.setText(creation.getType());
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
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new MaterialDialog.Builder(v.getContext())
                        .title("删除作品")
                        .positiveText("确定")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                mCreationList.remove(position);
                                mAdapter.notifyDataSetChanged();
                                Log.d(TAG, "onClick: 从adapter中传creation给presenter" + creation.getCreationId());
                                mPresenter.deleteCreation(creation);
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCreationList.size();
    }

    public void setmPresenter(ShowCreationContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
