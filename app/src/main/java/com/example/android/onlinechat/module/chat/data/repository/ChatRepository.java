package com.example.android.onlinechat.module.chat.data.repository;

import com.example.android.onlinechat.module.chat.di.ChatScope;
import com.example.android.onlinechat.module.chat.domain.model.MessageEntity;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public interface ChatRepository {
    void pushNewMessage(MessageEntity message);
}
