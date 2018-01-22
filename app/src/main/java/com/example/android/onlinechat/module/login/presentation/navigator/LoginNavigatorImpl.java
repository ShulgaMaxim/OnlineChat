package com.example.android.onlinechat.module.login.presentation.navigator;

import com.example.android.onlinechat.module.chat.presentation.view.ChatActivity;
import com.example.android.onlinechat.module.login.di.LoginScope;

import android.content.Context;
import android.content.Intent;

/**
 * @author mshulga
 * @since 18.01.18
 */

@LoginScope
public class LoginNavigatorImpl implements LoginNavigator {

    private final Context mContext;

    public LoginNavigatorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void goToChatScreen(String nickname) {
        Intent intent = new Intent(mContext, ChatActivity.class);
        intent.putExtra("nick", nickname);
        mContext.startActivity(intent);
    }
}
