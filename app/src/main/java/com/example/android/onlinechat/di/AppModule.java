package com.example.android.onlinechat.di;

import com.example.android.onlinechat.module.logout.LogoutUseCase;
import com.example.android.onlinechat.module.logout.LogoutUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mshulga
 * @since 22.01.18
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    LogoutUseCase provideLogoutUseCase() {
        return new LogoutUseCaseImpl();
    }
}
