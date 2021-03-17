package com.lhz.testandroid.launcheApp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lhz.testandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch_app_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                Intent intent=new Intent();
                intent =packageManager.getLaunchIntentForPackage("cn.krcom.tv");
                if(intent==null){
                    Toast.makeText(MainActivity.this, "未安装", Toast.LENGTH_LONG).show();
                }else{
                    startActivity(intent);
                }
            }
        });
    }
}
