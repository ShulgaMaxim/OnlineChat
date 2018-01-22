package com.example.android.onlinechat.module.chat.di;

import com.example.android.onlinechat.module.chat.presentation.view.ChatActivity;

import dagger.Subcomponent;

/**
 * @author mshulga
 * @since 22.01.18
 */
@Subcomponent(modules = ChatModule.class)
@ChatScope
public interface ChatComponent {
    void inject(ChatActivity chatActivity);
}
