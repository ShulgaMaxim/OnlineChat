package com.example.android.onlinechat.module.login.data.repository;

import com.example.android.onlinechat.module.login.data.model.UserDto;
import com.example.android.onlinechat.module.login.di.LoginScope;

import io.reactivex.Single;

/**
 * @author mshulga
 * @since 20.01.18
 */

@LoginScope
public interface LoginRepository {
    Single<UserDto> login();
}
