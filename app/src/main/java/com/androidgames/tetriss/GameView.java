package com.androidgames.tetriss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    static final int SZEROKOSC = 15;
    static final int WYSOKOSC = 30;

    private SpriteObject sprite;
    private GameLogic mGameLogic;
    private Paint paint;

    /////////////////////////////////////
    static int[][] tabr;
    Tetormino tetormino;
/////////////////////////////////////

    public GameView(Context context){
        super(context);
        setFocusable(true);


        mGameLogic = new GameLogic(getHolder(), this);
        getHolder().addCallback(this);
//////////////////////////////////////////////////////////////////////

        tabr = new int[WYSOKOSC+1][SZEROKOSC];
        tetormino = new Tetormino();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
//////////////////////////////////////////////////////////////////////
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mGameLogic.setGameState(GameLogic.RUNNING);
        mGameLogic.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void on_draw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);

        tetormino.paint(canvas, paint);




    }



    public void czyWszystkoZapelnione() {
        Arrays.fill(tabr[30],1);
        int wiersz = 29 ;
        while (wiersz > 0) {
            float zapelnione = 1;
            for (int j = 0; j<15; j++)
                zapelnione = zapelnione * Math.signum(tabr[29][j]);////////
            if (zapelnione > 0) {
                wiersz++;
                for (int i =29; i>0; i--)
                    System.arraycopy(tabr[i-1],0, tabr[i],0, 15);
            } else
                wiersz--;
        }
    }


    public void update(){

        czyWszystkoZapelnione();

        if (tetormino.dotkniecieZiemi())
        {

            tetormino.naZiemi();
            tetormino = new Tetormino();
        } else {
            tetormino.padanieNaPole();

        }
    }

    @Override
    public boolean onTouchEvent( MotionEvent event) {

        if((event.getX() > 0 && event.getX() <= 540)&& event.getY()<960)
            tetormino.ruch(-1);
        else if( event.getX() > 540 && event.getY()<960) tetormino.ruch(1) ;

        else if (event.getY()>=960) tetormino.obracanie();

        return false;
    }

    //////////////////////////



}