package com.example.android.onlinechat.module.chat.presentation.navigator;

import com.example.android.onlinechat.module.chat.di.ChatScope;
import com.example.android.onlinechat.module.login.presentation.view.LoginActivity;

import android.content.Context;
import android.content.Intent;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public class ChatNavigatorImpl implements ChatNavigator {

    private final Context mContext;

    public ChatNavigatorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void goToLoginScreen() {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
