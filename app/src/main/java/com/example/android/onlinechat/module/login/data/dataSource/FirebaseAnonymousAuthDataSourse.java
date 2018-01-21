package com.example.android.onlinechat.module.login.data.dataSource;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.android.onlinechat.module.login.data.model.FirebaseUserToUserDtoMapper;
import com.example.android.onlinechat.module.login.data.model.UserDto;
import com.example.android.onlinechat.module.login.di.LoginScope;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * @author mshulga
 * @since 21.01.18
 */
@LoginScope
public class FirebaseAnonymousAuthDataSourse implements FirebaseAuthDataSource {

    private final FirebaseUserToUserDtoMapper mToUserDtoMapper;

    public FirebaseAnonymousAuthDataSourse(FirebaseUserToUserDtoMapper toUserDtoMapper) {
        mToUserDtoMapper = toUserDtoMapper;
    }

    @Override
    public Maybe<UserDto> getCurrentUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null ? Maybe.just(mToUserDtoMapper.map(currentUser)) : Maybe.empty();
    }

    @Override
    public Single<UserDto> signNewUser() {
        return RxFirebaseAuth.signInAnonymously(FirebaseAuth.getInstance())
            .map(AuthResult::getUser)
            .flatMapSingle(user -> Single.just(mToUserDtoMapper.map(user)));
    }
}
