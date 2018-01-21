package com.example.android.onlinechat.module.login.data.repository;

import com.example.android.onlinechat.module.login.data.dataSource.FirebaseAuthDataSource;
import com.example.android.onlinechat.module.login.data.model.UserDto;
import com.example.android.onlinechat.module.login.di.LoginScope;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * @author mshulga
 * @since 20.01.18
 */
@LoginScope
public class LoginRepositoryImpl implements LoginRepository {

    private final FirebaseAuthDataSource mFirebaseAuthDataSource;

    public LoginRepositoryImpl(FirebaseAuthDataSource firebaseAuthDataSource) {
        mFirebaseAuthDataSource = firebaseAuthDataSource;
    }

    @Override
    public Single<UserDto> login() {
        return mFirebaseAuthDataSource.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .switchIfEmpty(mFirebaseAuthDataSource.signNewUser());
    }
}
