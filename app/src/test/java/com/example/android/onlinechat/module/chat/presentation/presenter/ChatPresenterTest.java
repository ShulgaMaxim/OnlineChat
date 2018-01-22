package com.example.android.onlinechat.module.chat.presentation.presenter;

import com.example.android.onlinechat.module.chat.domain.interactor.ChatInteractor;
import com.example.android.onlinechat.module.chat.presentation.navigator.ChatNavigator;
import com.example.android.onlinechat.module.chat.presentation.view.ChatView;
import com.example.android.onlinechat.module.logout.LogoutUseCase;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author mshulga
 * @since 23.01.18
 */

public class ChatPresenterTest {
    private ChatInteractor mChatInteractor;
    private ChatNavigator mChatNavigator;
    private LogoutUseCase mLogoutUseCase;
    private ChatView mChatView;
    private ChatPresenter mChatPresenter;

    @Before
    public void beforeTest() {
        mChatInteractor = mock(ChatInteractor.class);
        mChatNavigator = mock(ChatNavigator.class);
        mLogoutUseCase = mock(LogoutUseCase.class);
        mChatView = mock(ChatView.class);

        mChatPresenter = new ChatPresenterImpl(mChatInteractor, mChatView, mChatNavigator, mLogoutUseCase);
    }

    @Test
    public void testSendEmptyMessage() {
        String nickname = "nickname";
        String emptyMessage = "";
        mChatPresenter.onSendMessageButtonClick(nickname, emptyMessage);

        verify(mChatInteractor, never()).pushNewMessage(nickname, emptyMessage);
    }

    @Test
    public void testSendMessage() {
        String nickname = "nickname";
        String message = "message";
        mChatPresenter.onSendMessageButtonClick(nickname, message);

        verify(mChatInteractor).pushNewMessage(nickname, message);
        verify(mChatView).clearMessageEditText();
    }

    @Test
    public void testSignOutClick() {
        mChatPresenter.onSignOutClick();

        verify(mLogoutUseCase).perform();
        verify(mChatView).destroySelf();
        verify(mChatNavigator).goToLoginScreen();
    }
}
