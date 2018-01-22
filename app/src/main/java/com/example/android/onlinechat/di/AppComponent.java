package com.example.android.onlinechat.di;

import com.example.android.onlinechat.module.chat.di.ChatComponent;
import com.example.android.onlinechat.module.chat.di.ChatModule;
import com.example.android.onlinechat.module.login.di.LoginComponent;
import com.example.android.onlinechat.module.login.di.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author mshulga
 * @since 21.01.18
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    LoginComponent plus(LoginModule loginModule);

    ChatComponent plus(ChatModule chatModule);
}
