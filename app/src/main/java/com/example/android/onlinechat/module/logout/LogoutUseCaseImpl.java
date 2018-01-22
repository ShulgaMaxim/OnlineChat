package com.example.android.onlinechat.module.logout;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

/**
 * @author mshulga
 * @since 22.01.18
 */
@Singleton
public class LogoutUseCaseImpl implements LogoutUseCase {

    @Override
    public void perform() {
        FirebaseAuth.getInstance().signOut();
    }
}
