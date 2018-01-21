package com.example.android.onlinechat.di;

/**
 * @author mshulga
 * @since 21.01.18
 */

public class Injector {
    private static Injector sInstance = new Injector();

    private AppComponent appComponent;

    private Injector() {
    }

    public static Injector getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
