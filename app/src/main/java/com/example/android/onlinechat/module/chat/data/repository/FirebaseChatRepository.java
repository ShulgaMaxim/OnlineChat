package com.example.android.onlinechat.module.chat.data.repository;

import com.google.firebase.database.FirebaseDatabase;

import com.example.android.onlinechat.module.chat.di.ChatScope;
import com.example.android.onlinechat.module.chat.domain.model.MessageEntity;

import static com.example.android.onlinechat.Constants.MESSAGES_DB_SCHEME;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public class FirebaseChatRepository implements ChatRepository {

    @Override
    public void pushNewMessage(MessageEntity message) {
        FirebaseDatabase.getInstance()
            .getReference()
            .child(MESSAGES_DB_SCHEME)
            .push()
            .setValue(message);
    }
}
