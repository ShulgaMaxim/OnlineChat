package com.example.android.onlinechat.module.login.domain.interactor;

import com.example.android.onlinechat.module.login.data.repository.LoginRepository;
import com.example.android.onlinechat.module.login.di.LoginScope;
import com.example.android.onlinechat.module.login.domain.model.UserEntity;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * @author mshulga
 * @since 20.01.18
 */

@LoginScope
public class LoginInteractorImpl implements LoginInteractor {

    private final LoginRepository mLoginRepository;

    public LoginInteractorImpl(LoginRepository loginRepository) {
        mLoginRepository = loginRepository;
    }

    @Override
    public Single<UserEntity> login(String nickname) {
        return mLoginRepository.login()
            .observeOn(Schedulers.computation())
            .map(userDto -> new UserEntity(userDto.getUid(), nickname));
    }
}
