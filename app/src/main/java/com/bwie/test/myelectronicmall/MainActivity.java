package com.bwie.test.myelectronicmall;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private int time = 3;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏原有标题
        getSupportActionBar().hide();
        //线程
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    time--;
                    try {
                        sleep(1000);
                        if (time == 0){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, MallActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
