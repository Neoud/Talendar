package com.example.talendar.edittaleorts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.talendar.MyApplication;
import com.example.talendar.R;
import com.example.talendar.data.talesolitaire.TaleSolitaire;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static com.example.talendar.edittaleorts.EditTaleOrTSActivity.CREATION_TAGS;

public class EditTSFragment extends Fragment implements EditTaleOrTsContract.View, View.OnClickListener {
    private EditTaleOrTsContract.Presenter mPresenter;

    private List<String> mTagList = new ArrayList<>();
    private ImageButton ieButtonBack, ieButtonSave, ieButtonChooseTags;
    private EditText etTitle, etContent;

    public EditTSFragment() {}

    public static EditTSFragment newInstance() {
        return new EditTSFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_ts, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ieButtonBack = view.findViewById(R.id.imageButton_edit_ts_back);
        ieButtonSave = view.findViewById(R.id.imageButton_edit_ts_save);
        ieButtonChooseTags = view.findViewById(R.id.imageButton_edit_ts_choose_tags);
        etTitle = view.findViewById(R.id.editText_edit_ts_title);
        etContent = view.findViewById(R.id.editText_edit_ts_content);
        ieButtonBack.setOnClickListener(this);
        ieButtonSave.setOnClickListener(this);
        ieButtonChooseTags.setOnClickListener(this);
    }

    @Override
    public void setPresenter(EditTaleOrTsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton_edit_ts_back:
                Intent intentBack = new Intent();
                getActivity().setResult(RESULT_OK, intentBack);
                getActivity().finish();
                break;
            case R.id.imageButton_edit_ts_save:
                TaleSolitaire ts = new TaleSolitaire();
                ts.setTitle(etTitle.getText().toString());
                ts.setContent(etContent.getText().toString());
                ts.setAuthor(MyApplication.userObjectId);
                ts.setAuthorName(MyApplication.nickName);
                ts.setTags(mTagList);
                mPresenter.saveTS(ts);
                Intent intent = new Intent();
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
                break;
            case R.id.imageButton_edit_ts_choose_tags:
                MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                        .title("选择故事标签")
                        .positiveText("确定")
                        .items(CREATION_TAGS)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                for (int i = 0; i < text.length; i ++) {
                                    Log.d(TAG, "onSelection: 选中的标签为" + text.length + text[i]);
                                    mTagList.add((String) text[i]);
                                }
                                return true;
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }
}
