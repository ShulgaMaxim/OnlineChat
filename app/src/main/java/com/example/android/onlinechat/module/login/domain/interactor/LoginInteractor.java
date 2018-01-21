package com.example.android.onlinechat.module.login.domain.interactor;

import com.example.android.onlinechat.module.login.di.LoginScope;
import com.example.android.onlinechat.module.login.domain.model.UserEntity;

import io.reactivex.Single;

/**
 * @author mshulga
 * @since 20.01.18
 */

@LoginScope
public interface LoginInteractor {
    Single<UserEntity> login(String nickname);
}
