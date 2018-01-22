package com.example.android.onlinechat.module.login.presentation.view;

import com.example.android.onlinechat.BaseActivity;
import com.example.android.onlinechat.ContentView;
import com.example.android.onlinechat.R;
import com.example.android.onlinechat.di.Injector;
import com.example.android.onlinechat.module.login.di.LoginComponent;
import com.example.android.onlinechat.module.login.di.LoginModule;
import com.example.android.onlinechat.module.login.presentation.presenter.LoginPresenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginView {

    private static final int SIGN_IN_REQUEST_CODE = 1;
    @BindView(R.id.enter_button)
    Button mEnterButton;
    @BindView(R.id.edit_nickname)
    EditText mNicknameEditText;

    @Inject
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
    public void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginModule loginModule = new LoginModule(this);
        LoginComponent loginComponent = Injector.getInstance().getAppComponent().plus(loginModule);
        loginComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroyView();
    }
}
