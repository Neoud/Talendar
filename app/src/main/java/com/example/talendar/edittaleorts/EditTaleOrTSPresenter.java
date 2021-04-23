package com.example.talendar.edittaleorts;

import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaire;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;

public class EditTaleOrTSPresenter implements EditTaleOrTsContract.Presenter {
    private TaleRemoteDataSource mTaleDataSource;
    private TaleSolitaireRemoteDataSource mTSDataSource;
    private EditTaleOrTsContract.View mEditTaleOrTSView;

    public EditTaleOrTSPresenter(TaleRemoteDataSource taleDataSource, TaleSolitaireRemoteDataSource TSDataSource, EditTaleOrTsContract.View editTaleOrTSView) {
        mTaleDataSource = taleDataSource;
        mTSDataSource = TSDataSource;
        mEditTaleOrTSView = editTaleOrTSView;

        mEditTaleOrTSView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveTale(Tale tale) {
        mTaleDataSource.saveTale(tale);
    }

    @Override
    public void saveTS(TaleSolitaire ts) {
        mTSDataSource.saveTS(ts);
    }
}
