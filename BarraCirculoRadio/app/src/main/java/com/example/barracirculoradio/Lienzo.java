package com.example.barracirculoradio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class Lienzo extends View {
    int radio;
    private Handler mHandler;
    Paint p;
    int x, y;
    public Lienzo(Context c, Integer radio){
        super(c);
        this.radio = radio;
        mHandler = new Handler();
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth();
        y = c.getHeight();
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        float centroX = x/2;
        float centroY = y/2;

        p.setColor(Color.RED);
        c.drawCircle(centroX, centroY, radio, p);

    }

}
