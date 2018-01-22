package com.example.android.onlinechat.module.chat.domain.interactor;

import com.example.android.onlinechat.module.chat.data.repository.ChatRepository;
import com.example.android.onlinechat.module.chat.di.ChatScope;
import com.example.android.onlinechat.module.chat.domain.model.MessageEntity;

/**
 * @author mshulga
 * @since 22.01.18
 */
@ChatScope
public class ChatInteractorImpl implements ChatInteractor {

    private final ChatRepository mChatRepository;

    public ChatInteractorImpl(ChatRepository chatRepository) {
        mChatRepository = chatRepository;
    }

    @Override
    public void pushNewMessage(String nickname, String messageBody) {
        mChatRepository.pushNewMessage(new MessageEntity(nickname, messageBody));
    }
}
