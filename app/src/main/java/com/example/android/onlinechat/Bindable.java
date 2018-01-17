package com.example.android.onlinechat;

/**
 * @author mshulga
 * @since 18.01.18
 */

public interface Bindable<T> {

    void bind(T subject);

    void unbind();
}
