package com.example.android.onlinechat.module;

/**
 * @author mshulga
 * @since 21.01.18
 */

public interface FromToMapper<F, T> {
    T map(F from);
}
