package com.example.android.onlinechat.module.login.presentation.presenter;

import com.example.android.onlinechat.module.login.di.LoginScope;

/**
 * @author mshulga
 * @since 18.01.18
 */
@LoginScope
public interface LoginPresenter {

    void onEnterButtonClick(String nickname);

    void onDestroyView();
}
