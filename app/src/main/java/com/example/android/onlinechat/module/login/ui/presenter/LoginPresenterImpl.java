package com.example.android.onlinechat.module.login.ui.presenter;

import com.example.android.onlinechat.module.login.ui.navigator.LoginNavigator;
import com.example.android.onlinechat.module.login.ui.view.LoginView;

import java8.util.Optional;

/**
 * @author mshulga
 * @since 18.01.18
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginNavigator mLoginNavigator;
    private Optional<LoginView> mViewOptional;

    public LoginPresenterImpl(LoginNavigator loginNavigator, LoginView view) {
        mLoginNavigator = loginNavigator;
        mViewOptional = Optional.of(view);
    }

    @Override
    public void onEnterButtonClick(String nickname) {
        mViewOptional.ifPresent(loginView -> {
            if(nickname.isEmpty()) {
                loginView.showEmptyNicknameError();
                return;
            }
            mLoginNavigator.goToChatScreen();
        });
    }

    @Override
    public void onDestroyView() {
        mViewOptional = Optional.empty();
        mLoginNavigator = null;
    }
}
