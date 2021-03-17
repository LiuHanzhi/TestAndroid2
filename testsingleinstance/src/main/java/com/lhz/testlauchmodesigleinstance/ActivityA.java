package com.lhz.testlauchmodesigleinstance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
//        startActivity(new Intent(this, ActivityB.class));
        Intent intent = new Intent();
        intent.setAction("com.lhz.testlaunchmodelsingletask.ActivityC");
        startActivity(intent);
    }
}
