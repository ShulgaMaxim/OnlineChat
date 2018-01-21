package com.example.android.onlinechat;

import com.example.android.onlinechat.di.AppComponent;
import com.example.android.onlinechat.di.DaggerAppComponent;
import com.example.android.onlinechat.di.Injector;

import android.app.Application;

/**
 * @author mshulga
 * @since 21.01.18
 */

public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        AppComponent appComponent = DaggerAppComponent.builder()
            .build();
        Injector.getInstance().setAppComponent(appComponent);
    }
}
