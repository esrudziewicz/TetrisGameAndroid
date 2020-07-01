package com.androidgames.tetriss;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class SpriteObject {




    private int x;
    private int y;
    private int r_bloki;
    private Paint paint;

    public SpriteObject( int x, int y, int r_bloki){


        this.x = x;
        this.y = y;
        this.r_bloki = r_bloki;


        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);



    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    public void draw(Canvas canvas, Paint paint){

        if(paint!=null) this.paint = paint;

        canvas.drawRect(x*r_bloki+1, y*r_bloki+1,
                x*r_bloki+1+r_bloki-2,y*r_bloki+1+r_bloki-2, this.paint);

    }

    public void update(){


    }
}
