package com.example.android.onlinechat.module.login.di;

import com.example.android.onlinechat.module.login.data.dataSource.FirebaseAnonymousAuthDataSourse;
import com.example.android.onlinechat.module.login.data.dataSource.FirebaseAuthDataSource;
import com.example.android.onlinechat.module.login.data.model.FirebaseUserToUserDtoMapper;
import com.example.android.onlinechat.module.login.data.repository.LoginRepository;
import com.example.android.onlinechat.module.login.data.repository.LoginRepositoryImpl;
import com.example.android.onlinechat.module.login.domain.interactor.LoginInteractor;
import com.example.android.onlinechat.module.login.domain.interactor.LoginInteractorImpl;
import com.example.android.onlinechat.module.login.presentation.navigator.LoginNavigator;
import com.example.android.onlinechat.module.login.presentation.navigator.LoginNavigatorImpl;
import com.example.android.onlinechat.module.login.presentation.presenter.LoginPresenter;
import com.example.android.onlinechat.module.login.presentation.presenter.LoginPresenterImpl;
import com.example.android.onlinechat.module.login.presentation.view.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author mshulga
 * @since 21.01.18
 */

@Module
public class LoginModule {

    private final LoginActivity mLoginActivity;

    public LoginModule(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    @Provides
    @LoginScope
    LoginPresenter provideLoginPresenter(LoginNavigator navigator, LoginInteractor interactor) {
        return new LoginPresenterImpl(navigator, mLoginActivity, interactor);
    }

    @Provides
    @LoginScope
    LoginNavigator provideLoginNavigator() {
        return new LoginNavigatorImpl(mLoginActivity);
    }

    @Provides
    @LoginScope
    LoginInteractor provideLoginInteractor(LoginRepository loginRepository) {
        return new LoginInteractorImpl(loginRepository);
    }

    @Provides
    @LoginScope
    LoginRepository provideLoginRepository(FirebaseAuthDataSource authDataSource) {
        return new LoginRepositoryImpl(authDataSource);
    }

    @Provides
    @LoginScope
    FirebaseAuthDataSource provideFirebaseAuthDataSource(FirebaseUserToUserDtoMapper toUserDtoMapper) {
        return new FirebaseAnonymousAuthDataSourse(toUserDtoMapper);
    }

    @Provides
    @LoginScope
    FirebaseUserToUserDtoMapper provideFirebaseUserToUserDtoMapper() {
        return new FirebaseUserToUserDtoMapper();
    }
}
