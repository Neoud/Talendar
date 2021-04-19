package com.example.talendar.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.talendar.R;
import com.google.android.material.snackbar.Snackbar;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class RegisterOrLoginFragment extends Fragment implements RegisterOrLoginContract.View{

    public static final int VIEW_CODE_ET_USERNAME = 1; //用户名输入框code
    public static final int VIEW_CODE_ET_PASSWORD = 2; //密码输入框code

    private RegisterOrLoginContract.Presenter mPresenter; // fragment持有的对应模块的presenter
    private View view; // fragment的布局

    private EditText ETusername, ETpassword;
    private Button btnRegisterOrLogin, btnForgetPassword;

    public RegisterOrLoginFragment() {}

    public static RegisterOrLoginFragment newInstance() {
        return new RegisterOrLoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_login, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ETusername = view.findViewById(R.id.editTextUsername);
        ETpassword = view.findViewById(R.id.editTextPassword);
        btnForgetPassword = view.findViewById(R.id.btnRegisterOrLogin);
        btnForgetPassword = view.findViewById(R.id.btnForgetPassword);
        btnRegisterOrLogin = view.findViewById(R.id.btnRegisterOrLogin);

        btnRegisterOrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = ETusername.getText().toString();
                String pd = ETpassword.getText().toString();
                // 校验手机号or邮箱&密码格式
                Log.d(TAG, "onClick: 开始校验手机号" + un + "\t" + pd);
                if (mPresenter.checkUsernameForm(un) && mPresenter.checkPasswordForm(pd)) {
                    // 格式正确则登录或注册并登录
                    Log.d(TAG, "onClick: 格式正确开始登录");
                    mPresenter.registerOrLoginIn(un, pd);
                }
            }
        });
    }

    /**
     * @description 初始化fragment的presenter
     * @param presenter 模块的presenter
     * @author Pontus
     * @date 2021/4/17 23:05
     */
    @Override
    public void setPresenter(RegisterOrLoginContract.Presenter presenter) {
        mPresenter = presenter;
        Log.d(TAG, "setPresenter: 给RegisterOrLoginFragment添加presenter引用成功");
    }

    /**
     * @description 输入输入框中的输入内容格式后 显示的反馈
     * @param code RegisterOrLoginFragment布局中组件的code
     * @param message 反馈的内容
     * @author Pontus
     * @date 2021/4/17 14:19
     */
    @Override
    public void showSnackBar(int code, String message) {
        switch (code) {
            case VIEW_CODE_ET_USERNAME:
                Snackbar.make(ETusername, message, Snackbar.LENGTH_LONG).show();
                break;
            case VIEW_CODE_ET_PASSWORD:
                Snackbar.make(ETpassword, message, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * @description 成功登录后设置返回值并返回用户信息页面
     * @param objectId 登录用户的用户id
     * @author Pontus
     * @date 2021/4/17 14:24
     */
    @Override
    public void handleRegisterOrLoginInInfo(String objectId) {
        Intent dataIntent = new Intent();
        dataIntent.putExtra("objectId", objectId);
        getActivity().setResult(RESULT_OK, dataIntent);
        Log.d(TAG, "handleRegisterOrLoginInInfo: 开始销毁注册登录页面");
        getActivity().finish();
    }

}
