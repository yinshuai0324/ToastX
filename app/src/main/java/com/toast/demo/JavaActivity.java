package com.toast.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.toast.ToastX;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        ToastX.with(this).show();
    }
}