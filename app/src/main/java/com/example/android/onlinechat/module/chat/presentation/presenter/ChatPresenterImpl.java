package com.example.android.onlinechat.module.chat.presentation.presenter;

import com.example.android.onlinechat.module.chat.di.ChatScope;
import com.example.android.onlinechat.module.chat.domain.interactor.ChatInteractor;
import com.example.android.onlinechat.module.chat.presentation.navigator.ChatNavigator;
import com.example.android.onlinechat.module.chat.presentation.view.ChatView;
import com.example.android.onlinechat.module.logout.LogoutUseCase;

import java8.util.Optional;

/**
 * @author mshulga
 * @since 22.01.18
 */

@ChatScope
public class ChatPresenterImpl implements ChatPresenter {

    private final ChatInteractor mChatInteractor;
    private final LogoutUseCase mLogoutUseCase;
    private ChatNavigator mChatNavigator;
    private Optional<ChatView> mChatViewOptional;

    public ChatPresenterImpl(ChatInteractor chatInteractor, ChatView chatView, ChatNavigator chatNavigator, LogoutUseCase logoutUseCase) {
        mChatInteractor = chatInteractor;
        mChatViewOptional = Optional.of(chatView);
        mChatNavigator = chatNavigator;
        mLogoutUseCase = logoutUseCase;
    }

    @Override
    public void onSendMessageButtonClick(String nickname, String message) {
        if (message.isEmpty()) {
            return;
        }
        mChatInteractor.pushNewMessage(nickname, message);
        mChatViewOptional.ifPresent(ChatView::clearMessageEditText);
    }

    @Override
    public void onViewDestroyed() {
        mChatViewOptional = Optional.empty();
        mChatNavigator = null;
    }

    @Override
    public void onSignOutClick() {
        mLogoutUseCase.perform();
        mChatViewOptional.ifPresent(chatView -> {
            mChatNavigator.goToLoginScreen();
            chatView.destroySelf();
        });
    }
}
