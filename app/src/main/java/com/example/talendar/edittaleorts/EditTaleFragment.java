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
import com.example.talendar.data.tale.Tale;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static com.example.talendar.edittaleorts.EditTaleOrTSActivity.CREATION_TAGS;

public class EditTaleFragment extends Fragment implements EditTaleOrTsContract.View, View.OnClickListener {
    private EditTaleOrTsContract.Presenter mPresenter;

    private List<String> mTagList = new ArrayList<>();
    private ImageButton ieButtonBack, ieButtonSave, ieButtonChooseTags;
    private EditText etTitle, etContent;

    public EditTaleFragment() {}

    public static EditTaleFragment newInstance() {
        return new EditTaleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: 开始返回故事layout");
        return inflater.inflate(R.layout.fragment_edit_tale, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: 故事layout创建成功");
        ieButtonBack = view.findViewById(R.id.imageButton_edit_tale_back);
        ieButtonSave = view.findViewById(R.id.imageButton_edit_tale_save);
        etTitle = view.findViewById(R.id.editText_edit_tale_title);
        etContent = view.findViewById(R.id.editText_edit_tale_content);
        ieButtonChooseTags = view.findViewById(R.id.imageButton_edit_tale_choose_tags);
        ieButtonBack.setOnClickListener(this);
        ieButtonSave.setOnClickListener(this);
        ieButtonChooseTags.setOnClickListener(this);
    }

    @Override
    public void setPresenter(EditTaleOrTsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton_edit_tale_back:
                Intent intentBack = new Intent();
                getActivity().setResult(RESULT_OK, intentBack);
                getActivity().finish();
                break;
            case R.id.imageButton_edit_tale_save:
                Tale tale = new Tale();
                tale.setTitle(etTitle.getText().toString());
                tale.setContent(etContent.getText().toString());
                tale.setAuthor(MyApplication.userObjectId);
                tale.setAuthorName(MyApplication.nickName);
                tale.setTags(mTagList);
                mPresenter.saveTale(tale);
                Intent intent = new Intent();
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
                break;
            case R.id.imageButton_edit_tale_choose_tags:
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
