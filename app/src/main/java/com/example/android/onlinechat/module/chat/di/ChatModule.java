package com.example.android.onlinechat.module.chat.di;

import com.example.android.onlinechat.module.chat.data.repository.ChatRepository;
import com.example.android.onlinechat.module.chat.data.repository.FirebaseChatRepository;
import com.example.android.onlinechat.module.chat.domain.interactor.ChatInteractor;
import com.example.android.onlinechat.module.chat.domain.interactor.ChatInteractorImpl;
import com.example.android.onlinechat.module.chat.presentation.navigator.ChatNavigator;
import com.example.android.onlinechat.module.chat.presentation.navigator.ChatNavigatorImpl;
import com.example.android.onlinechat.module.chat.presentation.presenter.ChatPresenter;
import com.example.android.onlinechat.module.chat.presentation.presenter.ChatPresenterImpl;
import com.example.android.onlinechat.module.chat.presentation.view.ChatActivity;
import com.example.android.onlinechat.module.logout.LogoutUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * @author mshulga
 * @since 22.01.18
 */
@Module
public class ChatModule {

    private final ChatActivity mChatActivity;

    public ChatModule(ChatActivity chatActivity) {
        mChatActivity = chatActivity;
    }

    @Provides
    @ChatScope
    ChatRepository provideChatRepository() {
        return new FirebaseChatRepository();
    }

    @Provides
    @ChatScope
    ChatInteractor provideChatInteractor(ChatRepository chatRepository) {
        return new ChatInteractorImpl(chatRepository);
    }

    @Provides
    @ChatScope
    ChatNavigator provideChatNavigator() {
        return new ChatNavigatorImpl(mChatActivity);
    }

    @Provides
    @ChatScope
    ChatPresenter provideChatPresenter(ChatInteractor chatInteractor, ChatNavigator navigator, LogoutUseCase logoutUseCase) {
        return new ChatPresenterImpl(chatInteractor, mChatActivity, navigator, logoutUseCase);
    }
}
