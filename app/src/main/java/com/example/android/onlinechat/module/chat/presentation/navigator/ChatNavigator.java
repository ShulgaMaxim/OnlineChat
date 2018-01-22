package com.example.android.onlinechat.module.chat.presentation.navigator;

import com.example.android.onlinechat.module.chat.di.ChatScope;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public interface ChatNavigator {
    void goToLoginScreen();
}
