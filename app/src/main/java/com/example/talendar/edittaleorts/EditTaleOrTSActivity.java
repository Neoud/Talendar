package com.example.talendar.edittaleorts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.talendar.R;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;

public class EditTaleOrTSActivity extends AppCompatActivity {
    public static final String CREATION_TYPE_TALE = "TALE";
    public static final String CREATION_TYPE_TS = "TS";
    public static final String[] CREATION_TAGS = {"tag1", "tag2", "tag3", "tag4"};

    private static final String TAG = "EditTaleOrTSActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tale_or_ts);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        switch (intent.getExtras().getString("CREATION_TYPE")) {
            case CREATION_TYPE_TALE:
                Log.d(TAG, "init: 这是故事，开始加载故事fragment");
                EditTaleFragment editTaleFragment = EditTaleFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_edit_tale_or_ts, editTaleFragment).commit();
                new EditTaleOrTSPresenter(new TaleRemoteDataSource(), new TaleSolitaireRemoteDataSource(), editTaleFragment);
                break;
            case CREATION_TYPE_TS:
                Log.d(TAG, "init: 这是接龙故事，开始加载接龙故事fragment");
                EditTSFragment editTSFragment = EditTSFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_edit_tale_or_ts, editTSFragment).commit();
                new EditTaleOrTSPresenter(new TaleRemoteDataSource(), new TaleSolitaireRemoteDataSource(), editTSFragment);
                break;
        }
    }
}
