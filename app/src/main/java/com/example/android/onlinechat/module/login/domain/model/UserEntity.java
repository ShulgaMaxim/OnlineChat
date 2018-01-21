package com.example.android.onlinechat.module.login.domain.model;

/**
 * @author mshulga
 * @since 20.01.18
 */

public class UserEntity {
    private final String mUid;
    private final String mNickName;

    public UserEntity(String uid, String nickName) {
        mUid = uid;
        mNickName = nickName;
    }

    public String getUid() {
        return mUid;
    }

    public String getNickName() {
        return mNickName;
    }
}
