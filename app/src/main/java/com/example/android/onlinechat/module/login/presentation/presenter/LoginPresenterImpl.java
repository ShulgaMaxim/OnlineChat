package com.example.android.onlinechat.module.login.presentation.presenter;

import com.example.android.onlinechat.module.login.di.LoginScope;
import com.example.android.onlinechat.module.login.domain.interactor.LoginInteractor;
import com.example.android.onlinechat.module.login.presentation.navigator.LoginNavigator;
import com.example.android.onlinechat.module.login.presentation.view.LoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import java8.util.Optional;

/**
 * @author mshulga
 * @since 18.01.18
 */
@LoginScope
public class LoginPresenterImpl implements LoginPresenter {

    private final LoginInteractor mLoginInteractor;
    private LoginNavigator mLoginNavigator;
    private Optional<LoginView> mViewOptional;

    public LoginPresenterImpl(LoginNavigator loginNavigator, LoginView view, LoginInteractor loginInteractor) {
        mLoginNavigator = loginNavigator;
        mViewOptional = Optional.of(view);
        mLoginInteractor = loginInteractor;
    }

    @Override
    public void onEnterButtonClick(String nickname) {
        mViewOptional.ifPresent(loginView -> {
            if (nickname.isEmpty()) {
                loginView.showEmptyNicknameError();
                return;
            }
            mLoginInteractor.login(nickname)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userEntity -> {
                    mLoginNavigator.goToChatScreen();
                }, throwable -> {
                    loginView.showToastMessage(throwable.getLocalizedMessage());
                });
        });
    }

    @Override
    public void onDestroyView() {
        mViewOptional = Optional.empty();
        mLoginNavigator = null;
    }
}
