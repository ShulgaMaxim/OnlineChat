package com.example.android.onlinechat.module.chat.domain.interactor;

import com.example.android.onlinechat.module.chat.di.ChatScope;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public interface ChatInteractor {
    void pushNewMessage(String nickname, String messageBody);
}
