package com.example.android.onlinechat.module.login.presentation.presenter;

import com.example.android.onlinechat.BuildConfig;
import com.example.android.onlinechat.module.login.domain.interactor.LoginInteractor;
import com.example.android.onlinechat.module.login.domain.model.UserEntity;
import com.example.android.onlinechat.module.login.presentation.navigator.LoginNavigator;
import com.example.android.onlinechat.module.login.presentation.view.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author mshulga
 * @since 18.01.18
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginPresenterTest {

    private LoginView mLoginView;
    private LoginNavigator mLoginNavigator;
    private LoginPresenter mLoginPresenter;
    private LoginInteractor mLoginInteractor;

    @Before
    public void beforeTest() {
        mLoginView = mock(LoginView.class);
        mLoginNavigator = mock(LoginNavigator.class);
        mLoginInteractor = mock(LoginInteractor.class);
        mLoginPresenter = new LoginPresenterImpl(mLoginNavigator, mLoginView, mLoginInteractor);
    }

    @Test
    public void testAuthSuccess() {
        String nickName = "Volodya";
        when(mLoginInteractor.login(nickName)).thenReturn(Single.just(new UserEntity("uuid", nickName)));

        mLoginPresenter.onEnterButtonClick(nickName);

        verify(mLoginNavigator).goToChatScreen();
        verify(mLoginView, never()).showEmptyNicknameError();
    }

    @Test
    public void testAuthFailed() {
        String nickName = "Volodya";
        String errorMsg = "error";
        when(mLoginInteractor.login(nickName)).thenReturn(Single.error(new Exception(errorMsg)));

        mLoginPresenter.onEnterButtonClick(nickName);

        verify(mLoginNavigator, never()).goToChatScreen();
        verify(mLoginView, never()).showEmptyNicknameError();
        verify(mLoginView).showToastMessage(errorMsg);
    }

    @Test
    public void testEmptyNicknameEnter() {
        String nickName = "";
        mLoginPresenter.onEnterButtonClick(nickName);

        verify(mLoginNavigator, never()).goToChatScreen();
        verify(mLoginView).showEmptyNicknameError();
    }
}
