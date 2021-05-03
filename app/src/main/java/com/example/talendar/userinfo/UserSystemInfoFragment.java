package com.example.talendar.userinfo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.talendar.R;
import com.example.talendar.edituserinfo.EditUserInfoActivity;
import com.example.talendar.login.RegisterOrLoginActivity;
import com.example.talendar.login.RegisterOrLoginFragment;
import com.shehuan.niv.NiceImageView;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

import static android.content.ContentValues.TAG;

public class UserSystemInfoFragment extends Fragment implements UserSystemInfoContract.View, View.OnClickListener {
    public static final int REQUEST_CODE_LOGIN = 1; //登陆注册处理code
    public static final int REQUEST_CODE_EDIT_USER_INFO = 2; //编辑用户信息处理code
    public static final int IMAGE_CODE = 99; //调用相册获取图片code
    public static final int REQUEST_EXTERNAL_STORAGE = 100;
    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE }; //读取权限

    private UserSystemInfoContract.Presenter mPresenter;
    private View view;
    private String mObjectId;

    private Button btnLogin, btnEditUserInfo;
    private NiceImageView niVProfile;
    private TextView tvNick, tvDesc, tvFollowNum, tvFansNum, tvLevel, tvSex, tvAge, tvArea, tvSchool, tvQuotes;
    private RelativeLayout relativeLayoutLogout;

    public UserSystemInfoFragment() {}

    public static UserSystemInfoFragment newInstance() {
        return new UserSystemInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_system_info, null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        niVProfile = view.findViewById(R.id.profile);
        tvNick = view.findViewById(R.id.text_user_name);
        tvDesc = view.findViewById(R.id.text_user_description);
        tvFollowNum = view.findViewById(R.id.text_follow_number);
        tvFansNum = view.findViewById(R.id.text_fans_number);
        tvLevel = view.findViewById(R.id.text_level);
        tvSex = view.findViewById(R.id.text_sex);
        tvAge = view.findViewById(R.id.text_age);
        tvArea = view.findViewById(R.id.text_area);
        tvSchool = view.findViewById(R.id.text_school);
        tvQuotes = view.findViewById(R.id.text_quotes);
        btnLogin = view.findViewById(R.id.button_login);
        relativeLayoutLogout = view.findViewById(R.id.relativeLayout_logout);
        btnEditUserInfo = view.findViewById(R.id.btn_edit_user_info);
        btnLogin.setOnClickListener(this);
        btnEditUserInfo.setOnClickListener(this);
        niVProfile.setOnClickListener(this);
        relativeLayoutLogout.setOnClickListener(this);
        tvFollowNum.setOnClickListener(this);
        tvFansNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                Log.d(TAG, "onClick: 开始跳转" + getActivity().getLocalClassName());
                Intent loginIntent = new Intent(getActivity(), RegisterOrLoginActivity.class);
                startActivityForResult(loginIntent, REQUEST_CODE_LOGIN);
                break;
            case R.id.profile:
                callGallery();
                break;
            case R.id.btn_edit_user_info:
                Intent editInfoInent = new Intent(getActivity(), EditUserInfoActivity.class);
                editInfoInent.putExtra("objectId", mObjectId);
                editInfoInent.putExtra("age", tvAge.getText().toString());
                editInfoInent.putExtra("area", tvArea.getText().toString());
                editInfoInent.putExtra("quotes", tvQuotes.getText().toString());
                editInfoInent.putExtra("school", tvSchool.getText().toString());
                editInfoInent.putExtra("sex", tvSex.getText().toString());
                editInfoInent.putExtra("nick", tvNick.getText().toString());
                editInfoInent.putExtra("desc", tvDesc.getText().toString());
                startActivityForResult(editInfoInent, REQUEST_CODE_EDIT_USER_INFO);
                break;
            case R.id.relativeLayout_logout:
                if (BmobUser.isLogin()) {
                    new MaterialDialog.Builder(getActivity())
                            .title("退出登录")
                            .content("确定退出登录吗？")
                            .positiveText("确定")
                            .negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    BmobUser.logOut();
                                    niVProfile.setImageResource(R.drawable.cat);
                                    btnLogin.setVisibility(View.VISIBLE);
                                    showUserInfo("用户xxx", "-", "-", "-", "-", "-", "-", "-", "-","-");
                                    mObjectId = null;
                                }
                            })
                            .show();
                } else {
                    showToast("请先登录！");
                }
            default:
                break;
        }
    }

    @Override
    public void setPresenter(UserSystemInfoContract.Presenter presenter) {
        mPresenter = presenter;
        Log.d(TAG, "setPresenter: set presenter for user and system info fragment done: " + mPresenter.toString());
    }

    @Override
    public void setLoginBtnVisibility(int visibility) {
        btnLogin.setVisibility(visibility);
    }

    @Override
    public void showProfile(Bitmap profile) {
        if (! (profile == null)) {
            niVProfile.setImageBitmap(profile);
        }
    }

    @Override
    public void showUserInfo(String nick, String desc, String followNum, String fansNum, String level, String sex, String age, String area, String school, String quotes) {
        if (nick.equals("-")) {
            tvNick.setText("用户xxx");
        } else {
            tvNick.setText(nick);
        }
        if (!desc.equals("-")){
            tvDesc.setText(desc);
        }
        tvFollowNum.setText(followNum);
        tvFansNum.setText(fansNum);
        tvLevel.setText(level);
        tvSex.setText(sex);
        tvAge.setText(age);
        tvArea.setText(area);
        tvSchool.setText(school);
        tvQuotes.setText(quotes);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                String objectId = data.getExtras().getString("objectId");
                mObjectId = objectId;
                Log.d(TAG, "onActivityResult: 成功返回用户信息页面(" + objectId + ")，并开始获取用户信息");
                mPresenter.getUserInfo(objectId);
                break;
            case IMAGE_CODE:
                Bitmap bm = null;
                ContentResolver resolver = getActivity().getContentResolver();
                try{
                    // 获得图片的uri
                    Uri originalUri = data.getData();
                    Log.d(TAG, "onActivityResult: 获取到图片url" + originalUri);
                    bm = MediaStore.Images.Media.getBitmap(resolver,originalUri);
                    showProfile(bm);
                    String path = getImagePath(originalUri, null);
                    BmobFile bmobFile = new BmobFile(new File(path));
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Log.d(TAG, "done: download url is " + bmobFile.getFileUrl());
                            } else {
                                Log.d(TAG, "done: download exception: " + e.toString());
                            }
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                    showToast("获取相册图片失败");
                }
                break;
            case REQUEST_CODE_EDIT_USER_INFO:
                tvAge.setText(data.getExtras().getString("age"));
                tvArea.setText(data.getExtras().getString("area"));
                tvQuotes.setText(data.getExtras().getString("quotes"));
                tvSchool.setText(data.getExtras().getString("school"));
                tvSex.setText(data.getExtras().getString("sex"));
                tvNick.setText(data.getExtras().getString("nick"));
                tvDesc.setText(data.getExtras().getString("desc"));
            default:
                break;
        }
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    private void callGallery(){
        int permission_WRITE = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission_READ = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permission_WRITE != PackageManager.PERMISSION_GRANTED || permission_READ != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        Intent getAlbum = new Intent(Intent.ACTION_PICK);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();

        }
        return path;

    }

}
