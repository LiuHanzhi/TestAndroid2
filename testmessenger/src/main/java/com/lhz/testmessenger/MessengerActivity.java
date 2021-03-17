package com.lhz.testmessenger;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lhz.testmessenger.bean.TestBean;
import com.lhz.testmessenger.service.MessengerService;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;


public class MessengerActivity extends Activity {

    private Button btn_get_data;
    private TextView tv_content;
    private Messenger mServiceMessenger;
    private Messenger mClientMessenger;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String value = msg.getData().getString("key");
            TestBean testBean = (TestBean) msg.getData().getSerializable("bean");
            if (testBean != null) {
                tv_content.setText(testBean.getValue());
            } else {
                tv_content.setText(value);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        mClientMessenger = new Messenger(mHandler);

//        Intent mIntent = new Intent(this, MessengerService.class);
        //1.隐式跳转,绑定应用内的service(需要声明package)
//        Intent mIntent = new Intent("com.lhz.testmessenger.service.MessengerService");
//        mIntent.setPackage("com.lhz.testmessenger");
//        bindService(mIntent, mconn, BIND_AUTO_CREATE);

        //2.隐式跳转,绑定应用外的service(绑定testservicemulit应用里的servie)
        Intent mIntent = new Intent("com.lhz.testservicemulit.service.MessengerService");
        mIntent.setPackage("com.lhz.testservicemulit");
        bindService(mIntent, mconn, BIND_AUTO_CREATE);

        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_get_data = (Button) findViewById(R.id.btn_get_data);
        btn_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 1;
                try {
                    if (mServiceMessenger != null) {
                        //将客户端messenger信使赋值给服务端(为了方便接受回传过来的数据)
                        msg.replyTo = mClientMessenger;
                        //服务端信使发送数据
                        mServiceMessenger.send(msg);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ServiceConnection mconn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取服务端的messenger信使
            mServiceMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mconn);
    }
}
