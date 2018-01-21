package com.example.android.onlinechat.module.login.data.model;

/**
 * @author mshulga
 * @since 20.01.18
 */

public class UserDto {

    private final String mUid;

    public UserDto(String uid) {
        mUid = uid;
    }

    public String getUid() {
        return mUid;
    }
}
