package com.example.android.onlinechat.module.login.data.model;

import com.google.firebase.auth.FirebaseUser;

import com.example.android.onlinechat.module.FromToMapper;

/**
 * @author mshulga
 * @since 21.01.18
 */

public class FirebaseUserToUserDtoMapper implements FromToMapper<FirebaseUser, UserDto> {

    @Override
    public UserDto map(FirebaseUser from) {
        return new UserDto(from.getUid());
    }
}
