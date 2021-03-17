package com.lhz.testandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    public static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 140; i++) {
            new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,0);
        }
    }


    private class MyTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Log.e("lhz", "start a asynTask:" + count++);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
