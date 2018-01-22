package com.example.android.onlinechat.module.chat.domain.model;

/**
 * @author mshulga
 * @since 22.01.18
 */

public class MessageEntity {

    private String mUserNickname;
    private String mText;
    private String mUid;

    public MessageEntity() {
    }

    public MessageEntity(String userNickname, String text) {
        mUserNickname = userNickname;
        mText = text;
    }

    public String getUserNickname() {
        return mUserNickname;
    }

    public void setUserNickname(String userNickname) {
        mUserNickname = userNickname;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }
}
