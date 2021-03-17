package com.lhz.testmessenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lhz.testmessenger.service.BindService;

public class BinderActicityA extends Activity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private BindService bindService = null;
    private boolean isBound = false;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBound = true;
            BindService.MyBinder binder = (BindService.MyBinder) service;
            bindService = binder.getService();
            int num = bindService.getRandomNumber();
            Log.v("hjz","numA="+num);
        }

        //client 和service连接意外丢失时，会调用该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v("hjz","onServiceDisconnected  A");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_main);
        findLayoutView();
        setLister();
        initData();

    }

    private void findLayoutView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
    }

    private void setLister() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn1:
                intent = new Intent(BinderActicityA.this, BindService.class);
                intent.putExtra("from", "ActivityA");
                bindService(intent,conn,BIND_AUTO_CREATE);
                break;
            case R.id.btn2:
                if (isBound){
                    isBound = false;
                    Log.v("hjz","ActicityA is unbindService");
                    unbindService(conn);
                }
                break;
            case R.id.btn3:
//                intent = new Intent(this, BinderActivityB.class);
//                startActivity(intent);
                break;
            case R.id.btn4:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("hjz", "ActivityA -> onDestroy");
    }
}
