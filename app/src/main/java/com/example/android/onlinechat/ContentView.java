package com.example.android.onlinechat;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author mshulga
 * @since 18.01.18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface ContentView {
    @LayoutRes int value();
}
