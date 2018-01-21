package com.example.android.onlinechat.module.login.di;

import com.example.android.onlinechat.module.login.presentation.view.LoginActivity;

import dagger.Subcomponent;

/**
 * @author mshulga
 * @since 21.01.18
 */
@Subcomponent(modules = LoginModule.class)
@LoginScope
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
