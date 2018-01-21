package com.example.android.onlinechat.module.login.presentation.navigator;

import com.example.android.onlinechat.module.chat.ui.view.ChatActivity;

import android.content.Context;
import android.content.Intent;

/**
 * @author mshulga
 * @since 18.01.18
 */

public class LoginNavigatorImpl implements LoginNavigator {

    private final Context mContext;

    public LoginNavigatorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void goToChatScreen() {
        Intent intent = new Intent(mContext, ChatActivity.class);
        mContext.startActivity(intent);
    }
}
