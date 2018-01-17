package com.example.android.onlinechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author mshulga
 * @since 17.01.18
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentView annotation = getClass().getAnnotation(ContentView.class);
        setContentView(annotation.value());
        ButterKnife.bind(this);
    }
}
