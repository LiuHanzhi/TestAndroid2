package com.lhz.testlaunchmodelsingletask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(getClass().getSimpleName());
    }

    public void click(View view) {
//        Handler handler = new Handler(); //一个进程只有一个UI线程,UI线程只有一个looper和messageQueue,所有的事件(包括startActivity,view的刷新和渲染等)都是都是通过发送Message的形式给这个Looper
//        for (int i = 0; i < 5; i++) {//所以加上这几行代码后,会Handler会先处理完这些Message,然后才执行点击按钮的UI刷新,及其点击后的事件处理。
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
        startActivity(new Intent(this, ActivityB.class));
    }
}
