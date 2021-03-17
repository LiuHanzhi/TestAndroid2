package com.lhz.testandroid.surfaceviewTest;

import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MyTimerTask extends TimerTask {
    private SurfaceHolder holder = null;
    private Paint paint = null;
    private int count = 0;

    public MyTimerTask(SurfaceHolder _holder) {
        holder = _holder;
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void run() {
        // TODO Auto-generated method stub  
        if (count > 40)//测试程序，大于20不再画了
        {
            clearDraw();
            count = 0;
        }

        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas(null);//锁整个画布  
            if (count % 2 == 0) {
                canvas.drawText(count + "", 50, count * 8, paint);
            } else {
                canvas.drawText(count + "", 150, count * 8, paint);
            }
        } catch (Exception e) {
            // TODO: handle exception  
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
        count++;
    }

    public void clearDraw() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas(null);
            canvas.drawColor(Color.BLACK);
        } catch (Exception e) {
            // TODO: handle exception  
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}  