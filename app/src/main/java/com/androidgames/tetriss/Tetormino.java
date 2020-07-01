package com.androidgames.tetriss;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.androidgames.tetriss.GameView.SZEROKOSC;
import static com.androidgames.tetriss.GameView.WYSOKOSC;


public class Tetormino {


    final int r_bloki = 50;

    final int[][][] MODEL= {
            {{1,0,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0},{3} },/////
            {{0,0,1,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3}},
            {{0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0}, {3}},
            {{1,1,1,0}, {0,1,0,0}, {0,0,0,0}, {0,0,0,0}, {3}},
            {{1,1,0,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3}},
            {{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}, {4}},
            {{0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0},{4} },
    };
    private ArrayList<SpriteObject> tetormino;
    private int[][] tab = new int[4][4];

    int model;int kolor;int rozmiar;
    Random los;
    private int x=2;
    private int y=0;

    public Tetormino() {

        los = new Random();
        tetormino = new ArrayList<SpriteObject>();
        model = los.nextInt(MODEL.length);/////////

        for (int i = 0; i<4; i++)
            System.arraycopy(MODEL[model][i],0,tab[i],0,MODEL[model][i].length);

        noweTetormino();
    }


    protected boolean dotkniecieZiemi() {

        for (SpriteObject bloczek:tetormino)/////
            if (GameView.tabr[bloczek.getY()+1][bloczek.getX()]>0)return true;
        return false;


    }
    protected void naZiemi() {
        kolor=MODEL[model][4][0];
        for (SpriteObject bloczek:tetormino)
            GameView.tabr[bloczek.getY()][bloczek.getX()]=kolor;///

    }
    protected void noweTetormino() {
        for (int x = 0; x<4; x++)
            for (int y = 0; y<4; y++)
                if (tab[y][x]==1)tetormino.add(new SpriteObject(x+this.x ,y+this.y,r_bloki));
    }
    protected boolean wyjscieZaPole() {
        for (SpriteObject bloczek:tetormino) {
            if (bloczek.getX()==24 || GameView.tabr[bloczek.getY()][bloczek.getX()+1]>0)//////////////
                return true;
            if (bloczek.getX()==0 || GameView.tabr[bloczek.getY()][bloczek.getX()-1]>0)
                return true;
        }
        return false;
    }
    protected void ruch (int obrot)
    {
        if (!wyjscieZaPole())
        {
            for (SpriteObject bloczek:tetormino)bloczek.setX(bloczek.getX()+obrot);
            x++;
        }

    }
    /////////

    protected void padanieNaPole() {
        for (SpriteObject bloczek:tetormino) {
            bloczek.setY(bloczek.getY() + 1);///
        }
        y++;

    }
    protected void padanie ()
    {
        while (!dotkniecieZiemi())
            padanieNaPole();
    }
    protected boolean nieprawidoweZapelnienie() {
        for (int x = 0;x<4; x++)
            for (int y = 0;y<4;y++)
            {
                if (this.x<0 || this.x>24) return true;
                if (GameView.tabr[this.y][this.x]>0) return true;
            }
        return false;
    }
    protected void obracanie() {
        rozmiar = MODEL[model][4][0];
        for (int i=0; i<rozmiar; i++)
            for (int j=i; j<rozmiar-1-i;j++) {
                int mod=tab[rozmiar-1-j][i];//////////
                tab[rozmiar-1-j][i] = tab[rozmiar-1-i][rozmiar-1-j];///////////
                tab[rozmiar-1-i][rozmiar-1-j] = tab[j][rozmiar-1-i];
                tab[j][rozmiar-1-i] = tab[i][j];
                tab[i][j]=mod;/////
            }
        if (!nieprawidoweZapelnienie()) {
            tetormino.clear();
            noweTetormino();
        }
    }

    protected void paint(Canvas k, Paint paint) {


        for (SpriteObject bloczek : tetormino) { bloczek.draw(k, null); }

        for (int x=0; x< SZEROKOSC; x++)
            for (int y=0; y<WYSOKOSC; y++) {
                if (GameView.tabr[y][x]>0) {
                    k.drawRect(x*r_bloki+1, y*r_bloki+1,
                            (x*r_bloki+1)+r_bloki-2,(y*r_bloki+1)+r_bloki-2, paint);///////
                }

            }

    }



}
