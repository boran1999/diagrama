package com.example.diagrama;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class diagrama extends View {
    Path sector = new Path();
    MainActivity activity;
    float[] mas1 = {};
    boolean[] bmas = {};
    int w, h;

    public diagrama(Context context) {
        super(context);
    }

    public diagrama(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        activity = (MainActivity) context;
    }
    public void siemens(String temp){
        String[] mas = temp.split(" ");
        mas1 = new float[mas.length];
        bmas = new boolean[mas1.length];
        for(int i = 0 ; i<mas1.length; i++){

            mas1[i] = Float.parseFloat(mas[i]);
        }
        int sum= 0;
        for(int i =0; i< mas1.length;i++){
            sum += mas1[i];
        }
        for(int i =0; i< mas1.length;i++){
            float tmp = (float)mas1[i]/sum;
            mas1[i]=(tmp *360);
        }
        invalidate();
    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w = canvas.getWidth();
        h = canvas.getHeight();
        Paint p = new Paint();
        int sumarc=0;
        for (int i = 0; i < mas1.length; i++) {
            if(bmas[i]==true){
                p.setColor(Color.RED);
                float kale = (float)mas1[i]/360 * 100;
                activity.setText(""+kale);
            }
            else {
                p.setColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            }
            canvas.drawArc(0, 0, w, w, sumarc, mas1[i], true, p);
            sumarc+=mas1[i];
            bmas[i]=false;

        }

    }
    public float getAngle(int x1 , int y1, int x2, int y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));
        if(angle < 0){
            angle += 360;
        }
        return angle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x1 = (int)event.getX();
            int y1 = (int)event.getY();
            double dist = Math.sqrt(((x1-w/2)*(x1-w/2))+((y1-h/2)*(y1-h/2)));
            if(dist < w){
                float ang = getAngle(x1, y1, w/2, h/2 );
                Log.i("ang", "maslen= "+ ang);
                int newsum=0;
                for (int i = 0; i < mas1.length; i++) {
                    newsum+=mas1[i];
                    if(ang<newsum){
                        bmas[i]=true;
                        break;
                    }
                }
            }
            invalidate();
        }
        return true;
    }
}