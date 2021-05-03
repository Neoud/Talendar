package com.example.talendar.talesolitare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.talendar.R;
import com.example.talendar.adapter.CreationTSAdapter;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.talesolitaire.TaleSolitaire;
import com.example.talendar.showcreationdetail.ShowCreationDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class TaleSolitaireFragmentShow extends Fragment implements TaleSolitaireContract.View {
    private TaleSolitaireContract.Presenter mPresenter;
    private CreationTSAdapter mCreationTSAdapter;
    private List<CreationHome> mCreationHomeList =new ArrayList<>();
    private int tag;

    public TaleSolitaireFragmentShow(int tag) {}

    public static TaleSolitaireFragmentShow newInstance(int tag) {
        return new TaleSolitaireFragmentShow(tag);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tale_solitaire_show, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_tale_solitare_show);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mCreationTSAdapter = new CreationTSAdapter(mCreationHomeList, new CreationTSAdapter.OnItemClickedListener() {
            @Override
            public void onClick(CreationHome creationHome) {
                Intent intent = new Intent(getActivity(), ShowCreationDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("creationHome", creationHome);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mCreationTSAdapter.setmPresenter((TaleSolitairePresenter)mPresenter);
        recyclerView.setAdapter(mCreationTSAdapter);
        mPresenter.initTaleSolitaireShow(tag);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setPresenter(TaleSolitaireContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void setCreationList(List<CreationHome> creationHomeList) {
        mCreationHomeList.addAll(creationHomeList);
        mCreationTSAdapter.notifyDataSetChanged();
    }
}
