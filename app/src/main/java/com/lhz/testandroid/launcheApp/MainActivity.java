package com.lhz.testandroid.launcheApp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lhz.testandroid.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch_app_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                Intent intent = new Intent();
                intent = packageManager.getLaunchIntentForPackage("cn.krcom.tv");
                if (intent == null) {
                    Toast.makeText(MainActivity.this, "未安装", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.et);
                startScheme(et.getText().toString(), -1);
            }
        });
    }


    /**
     * schem地址是否有效
     *
     * @param scheme
     * @return
     */
    private boolean isSchemeValid(String scheme) {
        if (TextUtils.isEmpty(scheme)) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }

    /**
     * 跳转到指定scheme
     *
     * @param scheme
     * @return
     */
    public boolean startScheme(String scheme, int intentFlag) {
        try {
            if (!isSchemeValid(scheme)) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
            if (intentFlag > 0) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intentFlag);
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            getApplication().startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
