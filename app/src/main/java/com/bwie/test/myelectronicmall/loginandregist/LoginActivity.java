package com.bwie.test.myelectronicmall.loginandregist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.test.myelectronicmall.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //隐藏原有标题
        getSupportActionBar().hide();
    }
}
