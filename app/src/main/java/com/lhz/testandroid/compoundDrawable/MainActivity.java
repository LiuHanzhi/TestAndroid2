package com.lhz.testandroid.compoundDrawable;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lhz.testandroid.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_compound_drawable);
        findViewById(R.id.button).setOnClickListener(this);
    }

    private boolean isOn;
    @Override
    public void onClick(View v) {
        if(!isOn) {
            final Drawable drawable = getResources().getDrawable(R.mipmap.information_list_item_detail_img_live);
            int height = 16;
            int width;
            if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
                width = drawable.getIntrinsicWidth() * height / drawable.getIntrinsicHeight();
            } else {
                width = height;
            }
            drawable.setBounds(0, 0, width, height);
            ((TextView) findViewById(R.id.text)).setCompoundDrawables(drawable, null, null, null);
        } else {
            ((TextView) findViewById(R.id.text)).setCompoundDrawables(null, null, null, null);
        }
        isOn = !isOn;
    }
}
