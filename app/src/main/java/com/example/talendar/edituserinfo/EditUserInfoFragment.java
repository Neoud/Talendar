package com.example.talendar.edituserinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.talendar.R;

public class EditUserInfoFragment extends Fragment implements EditUserInfoContract.View, View.OnClickListener, MaterialDialog.InputCallback {

    private EditUserInfoContract.Presenter mEditUserInfoPresenter;
    private View view;
    private String mObjectId;

    private LinearLayout editSex, editAge, editArea, editSchool, editQuotes, editNick, editDesc;
    private TextView textSex, textAge, textArea, textSchool, textQuotes, textNick, textDesc;
    private Button btnOk;

    public EditUserInfoFragment() {}

    public static EditUserInfoFragment newInstance() {
        return new EditUserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_user_info, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editAge = view.findViewById(R.id.relativeLayout_edit_age);
        editArea = view.findViewById(R.id.relativeLayout_edit_area);
        editQuotes = view.findViewById(R.id.relativeLayout_edit_quotes);
        editSchool = view.findViewById(R.id.relativeLayout_edit_school);
        editSex = view.findViewById(R.id.relativeLayout_edit_sex);
        editNick = view.findViewById(R.id.relativeLayout_edit_nick);
        editDesc = view.findViewById(R.id.relativeLayout_edit_desc);
        textAge = view.findViewById(R.id.text_age);
        textArea = view.findViewById(R.id.text_area);
        textQuotes = view.findViewById(R.id.text_quotes);
        textSchool = view.findViewById(R.id.text_school);
        textSex = view.findViewById(R.id.text_sex);
        textNick = view.findViewById(R.id.text_nick);
        textDesc = view.findViewById(R.id.text_desc);
        btnOk = view.findViewById(R.id.btn_edit_user_info_ok);
        editAge.setOnClickListener(this);
        editArea.setOnClickListener(this);
        editQuotes.setOnClickListener(this);
        editSchool.setOnClickListener(this);
        editSex.setOnClickListener(this);
        editNick.setOnClickListener(this);
        editDesc.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        Intent intent =  getActivity().getIntent();
        init(intent.getExtras().getString("age"),intent.getExtras().getString("area"),intent.getExtras().getString("quotes"),intent.getExtras().getString("school"),intent.getExtras().getString("sex"),intent.getExtras().getString("nick"),intent.getExtras().getString("desc"));
    }

    @Override
    public void setPresenter(EditUserInfoContract.Presenter presenter) {
        mEditUserInfoPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativeLayout_edit_age:
                getMaterialDialog("年龄", "年龄", this).show();
                break;
            case R.id.relativeLayout_edit_area:
                getMaterialDialog("地区", "地区", this).show();
                break;
            case R.id.relativeLayout_edit_quotes:
                getMaterialDialog("座右铭", "座右铭", this).show();
                break;
            case R.id.relativeLayout_edit_school:
                getMaterialDialog("学校", "学校", this).show();
                break;
            case R.id.relativeLayout_edit_sex:
                getMaterialDialog("性别", "性别", this).show();
                break;
            case R.id.relativeLayout_edit_nick:
                getMaterialDialog("昵称", "昵称", this).show();
                break;
            case R.id.relativeLayout_edit_desc:
                new MaterialDialog.Builder(getActivity())
                        .title("简介")
                        .input("简介", null, this)
                        .inputRange(2, 10)
                        .positiveText("确定")
                        .build();
                break;
            case R.id.btn_edit_user_info_ok:
                Intent intentOk = new Intent();
                intentOk.putExtra("age", textAge.getText().toString());
                intentOk.putExtra("area", textArea.getText().toString());
                intentOk.putExtra("quotes", textQuotes.getText().toString());
                intentOk.putExtra("school", textSchool.getText().toString());
                intentOk.putExtra("sex", textSex.getText().toString());
                intentOk.putExtra("nick", textNick.getText().toString());
                intentOk.putExtra("desc", textDesc.getText().toString());
                getActivity().setResult(Activity.RESULT_OK, intentOk);
                getActivity().finish();
            default:
                break;
            }
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
        switch (dialog.getTitleView().getText().toString()) {
            case "年龄":
                textAge.setText(input.toString());
                mEditUserInfoPresenter.saveAge(input.toString(), mObjectId);
                break;
            case "地区":
                textArea.setText(input.toString());
                mEditUserInfoPresenter.saveArea(input.toString(), mObjectId);
                break;
            case "座右铭":
                textQuotes.setText(input.toString());
                mEditUserInfoPresenter.saveQuotes(input.toString(), mObjectId);
                break;
            case "学校":
                textSchool.setText(input.toString());
                mEditUserInfoPresenter.saveSchool(input.toString(), mObjectId);
                break;
            case "性别":
                textSex.setText(input.toString());
                mEditUserInfoPresenter.saveSex(input.toString(), mObjectId);
                break;
            case "昵称":
                textNick.setText(input.toString());
                mEditUserInfoPresenter.saveNick(input.toString(), mObjectId);
                break;
            case "简介":
                textDesc.setText(input.toString());
                mEditUserInfoPresenter.saveDesc(input.toString(), mObjectId);
                break;
            default:
                break;
        }
    }

    @Override
    public void init(String age, String area, String quotes, String school, String sex, String nick, String desc) {
        textAge.setText(age);
        textArea.setText(area);
        textQuotes.setText(quotes);
        textSchool.setText(school);
        textSex.setText(sex);
    }

    private MaterialDialog getMaterialDialog(String title, String hint, MaterialDialog.InputCallback callback) {
        return new MaterialDialog.Builder(getActivity())
                .title(title)
                .input(hint, null, callback)
                .positiveText("确定")
                .build();
    }

    @Override
    public void setObjectId(String objectId) {
        mObjectId = objectId;
    }
}
