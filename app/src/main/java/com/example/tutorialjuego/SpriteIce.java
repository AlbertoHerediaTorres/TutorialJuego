package com.example.tutorialjuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;


public class SpriteIce {
    private static final int BMP_ROWS = 5;
    private static final int BMP_COLUMNS = 6;
    public int x;
    public int y;
    private int xSpeed = -20;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;



    public SpriteIce(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;

        y = (gameView.getWidth() /2 * 2 + 50) + 200;
        x = (gameView.getHeight() /2 );

    }

    private void update() {

        if (x > gameView.getWidth() - width - xSpeed) {
            xSpeed = -20;
        }

        if (x + xSpeed < 0) {
            xSpeed = 20;
        }
        x = x + xSpeed;


        currentFrame = ++currentFrame % BMP_COLUMNS;

    }

    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = 1 * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width + 50, y + height+ 50); // aqui agrandamos el spiteIce con el +50
        canvas.drawBitmap(bmp, src, dst, null);
    }

}