package com.example.android.onlinechat.module.login.ui.presenter;

import com.example.android.onlinechat.module.login.ui.navigator.LoginNavigator;
import com.example.android.onlinechat.module.login.ui.view.LoginView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author mshulga
 * @since 18.01.18
 */

public class LoginPresenterTest {

    private LoginView mLoginView;
    private LoginNavigator mLoginNavigator;
    private LoginPresenter mLoginPresenter;

    @Before
    public void beforeTest() {
        mLoginView = mock(LoginView.class);
        mLoginNavigator = mock(LoginNavigator.class);
        mLoginPresenter = new LoginPresenterImpl(mLoginNavigator, mLoginView);
    }

    @Test
    public void testValidNicknameEnter(){
        String nickName = "Volodya";
        mLoginPresenter.onEnterButtonClick(nickName);

        verify(mLoginNavigator).goToChatScreen();
        verify(mLoginView, never()).showEmptyNicknameError();
    }

    @Test
    public void testEmptyNicknameEnter(){
        String nickName = "";
        mLoginPresenter.onEnterButtonClick(nickName);

        verify(mLoginNavigator, never()).goToChatScreen();
        verify(mLoginView).showEmptyNicknameError();
    }
}
