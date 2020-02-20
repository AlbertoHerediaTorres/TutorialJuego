package com.example.tutorialjuego;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;


public class Sprite {
    private static final int BMP_ROWS = 5;
    private static final int BMP_COLUMNS = 6;
    private int x = 10;
    private int y;
    private int xSpeed = 150;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;



    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;


        y = (gameView.getWidth() / 2 * 2)+200;


    }

    private void update() {
         /*if (x > gameView.getWidth() - width - xSpeed) {
            xSpeed = -30;
        }

        if (x + xSpeed < 0) {
            xSpeed = 30;
        }
        x = x + xSpeed;
*/

        currentFrame = ++currentFrame % BMP_COLUMNS;



    }

    public int saltar(){


        y = y - xSpeed;

        try {
            Thread.sleep(1500);
            y = y + xSpeed;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x;



    }


    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = 1 * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width + 100, y + height+ 100); // aqui agrandamos el spite con el +100
        canvas.drawBitmap(bmp, src, dst, null);
    }

}
