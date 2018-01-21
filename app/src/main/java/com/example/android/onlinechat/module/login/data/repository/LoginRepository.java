package com.example.android.onlinechat.module.login.data.repository;

import com.example.android.onlinechat.module.login.data.model.UserDto;

import io.reactivex.Single;

/**
 * @author mshulga
 * @since 20.01.18
 */

public interface LoginRepository {
    Single<UserDto> login();
}
