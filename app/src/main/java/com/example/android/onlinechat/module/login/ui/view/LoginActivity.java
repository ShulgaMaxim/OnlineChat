package com.example.android.onlinechat.module.login.ui.view;

import com.example.android.onlinechat.BaseActivity;
import com.example.android.onlinechat.ContentView;
import com.example.android.onlinechat.R;
import com.example.android.onlinechat.module.login.ui.navigator.LoginNavigatorImpl;
import com.example.android.onlinechat.module.login.ui.presenter.LoginPresenter;
import com.example.android.onlinechat.module.login.ui.presenter.LoginPresenterImpl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.enter_button)
    Button mEnterButton;
    @BindView(R.id.edit_nickname)
    EditText mNicknameEditText;

    LoginPresenter mLoginPresenter;

    @OnClick(R.id.enter_button)
    public void enterBtnClick() {
        mLoginPresenter.onEnterButtonClick(mNicknameEditText.getText().toString());
    }

    @Override
    public void showEmptyNicknameError() {
        mNicknameEditText.setError("Никнейм должен быть заполнен");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroyView();
    }

    private void initPresenter() {
        LoginNavigatorImpl loginNavigator = new LoginNavigatorImpl(this);
        mLoginPresenter = new LoginPresenterImpl(loginNavigator, this);
    }
}
