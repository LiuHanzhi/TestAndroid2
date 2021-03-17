package com.lhz.testmessenger.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lhz.testmessenger.bean.TestBean;

public class MessengerService extends Service {

    public static final String TAG = "MessengerService";
    public static final int MSG_GET = 1;

    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET:
                    //获取客户端的messenger
                    Messenger clientMessenger = msg.replyTo;
                    if (clientMessenger != null) {
                        Message newMsg = Message.obtain();
                        try {
                            TestBean testBean = new TestBean();
                            testBean.setKey(1);
                            testBean.setValue("test");
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("bean", testBean);
                            bundle.putString("key", "MessengerService服务端回传的数据");
                            newMsg.setData(bundle);
                            clientMessenger.send(newMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}