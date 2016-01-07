package com.example.rain.stop_watch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rain on 1/7/2016.
 */
public class ClockView extends View {

    private float centerX;
    private float centerY;
    private float radius = 200;
    protected Canvas canvas;


    public ClockView(Context context) {
        super(context);
    }

    /**
     * @param canvas
     * @param length
     * @param alpha: is angle, come from 0 to 360
     */
    protected void drawLine(Canvas canvas, float length, float alpha) {
        alpha -= 90;

        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(4);

        float stopX = (float) (this.centerX + length * Math.cos(alpha / 180 * Math.PI));
        float stopY = (float) (this.centerY + (float) length * Math.sin(alpha / 180 * Math.PI));
        canvas.drawLine(this.centerX, this.centerY, stopX, stopY, linePaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.centerX = getWidth() / 2;
        this.centerY = getHeight() / 2;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(this.centerX, this.centerY, this.radius, paint);

        Calendar calendar = Calendar.getInstance();
        float seconds = calendar.get(Calendar.SECOND);
        float minutes = calendar.get(Calendar.MINUTE);
        float hours = calendar.get(Calendar.HOUR);
        System.out.println(hours);
        float radius = this.radius;

        drawLine(canvas, radius, seconds / 60 * 360);
        drawLine(canvas, radius / 5 * 4, minutes / 60 * 360);
        drawLine(canvas, radius / 2, hours / 12 * 360);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.invalidate();
    }

}
