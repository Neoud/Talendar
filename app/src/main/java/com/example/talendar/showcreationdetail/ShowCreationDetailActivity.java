package com.example.talendar.showcreationdetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talendar.R;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.comment.CommentRemoteDataSource;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;

public class ShowCreationDetailActivity extends AppCompatActivity {

    private ShowTaleDetailFragment showTaleDetailFragment;
    private ShowTSDetailFragment showTSDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_creation_detail);
        CreationHome creationHome = getIntent().getParcelableExtra("creationHome");
        switch (creationHome.getCreationType()) {
            case 0:
                showTaleDetailFragment = ShowTaleDetailFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_show_creation_detail_activity, showTaleDetailFragment).commit();
                new ShowTaleDetailPresenter(showTaleDetailFragment, new TaleRemoteDataSource(), new CommentRemoteDataSource(), creationHome);
                break;
            case 1:
                showTSDetailFragment = ShowTSDetailFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_show_creation_detail_activity, showTSDetailFragment).commit();
                new ShowTSDetailPresenter(showTSDetailFragment, new TaleSolitaireRemoteDataSource(), new CommentRemoteDataSource(), creationHome);
                break;
            default:
                break;
        }
    }
}
