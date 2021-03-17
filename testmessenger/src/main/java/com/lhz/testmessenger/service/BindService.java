package com.lhz.testmessenger.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class BindService extends Service {

    public class MyBinder extends Binder {

        public BindService getService() {
            return BindService.this;
        }
    }

    //通过binder实现了 调用者（client）与 service之间的通信
    private MyBinder binder = new MyBinder();

    private final Random generator = new Random();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("hjz", "BindService -> onCreate, Thread: " + Thread.currentThread().getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("hjz", "BindService -> onBind, Thread: " + Thread.currentThread().getName());
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("hjz", "BindService -> onUnbind, from:" + intent.getStringExtra("from"));
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("hjz", "BindService -> onStartCommand, startId: " + startId + ", Thread: " + Thread.currentThread().getName());
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("hjz", "BindService -> onDestroy, Thread: " + Thread.currentThread().getName());
        super.onDestroy();
    }

    //在Service中暴露出去的方法，供client调用
    public int getRandomNumber() {
        return generator.nextInt();
    }
}