package com.example.talendar.showcreation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.talendar.MyApplication;
import com.example.talendar.R;
import com.example.talendar.adapter.CreationAdapter;
import com.example.talendar.data.bean.Creation;
import com.example.talendar.edittaleorts.EditTaleOrTSActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobUser;
import static android.content.ContentValues.TAG;
import static com.example.talendar.edittaleorts.EditTaleOrTSActivity.CREATION_TYPE_TALE;
import static com.example.talendar.edittaleorts.EditTaleOrTSActivity.CREATION_TYPE_TS;

public class MyCreationFragment extends Fragment implements ShowCreationContract.View {
    private final String[] creationType = {"故事", "接龙故事"};
    public static final int REQUEST_CODE_EDIT_TALE_OR_TS = 200;

    private ShowCreationContract.Presenter mPresenter;

    private FloatingActionButton mFloatingActionButton;

    private List<Creation> mCreationList = new ArrayList<>();
    private CreationAdapter creationAdapter;

    public MyCreationFragment() {}

    public static MyCreationFragment newInstance() {
        return new MyCreationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BmobUser.isLogin()) {
            View view = inflater.inflate(R.layout.fragment_my_creation, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_my_creation);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            creationAdapter = new CreationAdapter(mCreationList);
            creationAdapter.setmPresenter(mPresenter);
            recyclerView.setAdapter(creationAdapter);
            mFloatingActionButton = view.findViewById(R.id.floatingActionButton_edit_tale_or_ts);
            mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(MyApplication.userObjectId == null)) {
                        new MaterialDialog.Builder(getContext())
                                .title("选择故事类型")
                                .items(creationType)
                                .itemsCallback(new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                        Log.d(TAG, "onSelection: position is " + position);
                                        switch (position) {
                                            case 0:
                                                Log.d(TAG, "onClick: 打开创建tale的fragment" + position);
                                                Intent intent0 = new Intent(getActivity(), EditTaleOrTSActivity.class);
                                                intent0.putExtra("CREATION_TYPE", CREATION_TYPE_TALE);
                                                startActivityForResult(intent0, REQUEST_CODE_EDIT_TALE_OR_TS);
                                                break;
                                            case 1:
                                                Log.d(TAG, "onClick: 打开创建ts的frament" + position);
                                                Intent intent1 = new Intent(getActivity(), EditTaleOrTSActivity.class);
                                                intent1.putExtra("CREATION_TYPE", CREATION_TYPE_TS);
                                                startActivityForResult(intent1, REQUEST_CODE_EDIT_TALE_OR_TS);
                                                break;
                                        }
                                    }
                                })
                                .show();
                    } else {
                        showToast("请先登录");
                    }
                }
            });
            mPresenter.start();
            return view;
        } else {
            return inflater.inflate(R.layout.common_not_login, null);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setPresenter(ShowCreationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCreationList(List<Creation> creationList) {
        mCreationList.addAll(creationList);
        Log.d(TAG, "setCreationList: 目前需加载总故事数为" + mCreationList.size());
        creationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_EDIT_TALE_OR_TS:
                Log.d(TAG, "onActivityResult: 清空展示队列");
                mCreationList.removeAll(mCreationList);
                Log.d(TAG, "onActivityResult: 重新加载故事列表");
                mPresenter.start();
                break;
        }
    }
}
