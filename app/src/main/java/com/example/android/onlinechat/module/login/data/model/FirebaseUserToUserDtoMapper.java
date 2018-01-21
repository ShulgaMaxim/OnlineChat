package com.example.android.onlinechat.module.login.data.model;

import com.google.firebase.auth.FirebaseUser;

import com.example.android.onlinechat.module.FromToMapper;
import com.example.android.onlinechat.module.login.di.LoginScope;

/**
 * @author mshulga
 * @since 21.01.18
 */
@LoginScope
public class FirebaseUserToUserDtoMapper implements FromToMapper<FirebaseUser, UserDto> {

    @Override
    public UserDto map(FirebaseUser from) {
        return new UserDto(from.getUid());
    }
}
