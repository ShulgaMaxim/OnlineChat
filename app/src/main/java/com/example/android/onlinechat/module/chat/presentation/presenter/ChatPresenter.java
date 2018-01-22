package com.example.android.onlinechat.module.chat.presentation.presenter;

import com.example.android.onlinechat.module.chat.di.ChatScope;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public interface ChatPresenter {

    void onSendMessageButtonClick(String nickname, String message);

    void onViewDestroyed();

    void onSignOutClick();
}
