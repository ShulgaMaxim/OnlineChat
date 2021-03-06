package com.example.android.onlinechat.module.login.data.dataSource;

import com.example.android.onlinechat.module.login.data.model.UserDto;
import com.example.android.onlinechat.module.login.di.LoginScope;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * @author mshulga
 * @since 21.01.18
 */
@LoginScope
public interface FirebaseAuthDataSource {
    Maybe<UserDto> getCurrentUser();

    Single<UserDto> signNewUser();
}
