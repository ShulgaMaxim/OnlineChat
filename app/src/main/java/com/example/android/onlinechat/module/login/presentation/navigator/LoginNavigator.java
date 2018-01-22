package com.example.android.onlinechat.module.login.presentation.navigator;

import com.example.android.onlinechat.module.login.di.LoginScope;

/**
 * @author mshulga
 * @since 18.01.18
 */
@LoginScope
public interface LoginNavigator {
    void goToChatScreen(String nickname);
}
