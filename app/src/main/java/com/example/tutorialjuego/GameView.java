package com.example.tutorialjuego;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;


public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private List<SpriteIce> spritesIce = new ArrayList<SpriteIce>();
    Drawable fondo = getResources().getDrawable(R.drawable.background); // Para obtener la imagen de fondo

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }


            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
    }

    private void createSprites() {
        sprites.add(createSprite(R.drawable.spritep));
        spritesIce.add(createSpriteIce(R.drawable.icleblock));

    }

    private Sprite createSprite(int resouce) {

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);

    }

    private SpriteIce createSpriteIce(int resouce) {

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new SpriteIce(this,bmp);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);

        // Obtener dimensiones del canvas
        int altoCanvas = getBottom();
        int anchoCanvas = getRight();
        float medioCanvas = (float)altoCanvas/anchoCanvas;

        //Obtener dimensiones de la imagen

        int altoImagen = fondo.getIntrinsicHeight();
        int anchoImagen = fondo.getIntrinsicWidth();
        float medioImagen = (float)altoImagen/anchoImagen;

        // Ajustar imagen al canvas
        fondo.setBounds(0,0,anchoCanvas, altoCanvas);
        fondo.draw(canvas);

        for (Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }

        for (SpriteIce spriteIce : spritesIce) {
            spriteIce.onDraw(canvas);
        }


    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP){
            Sprite sprite = sprites.get(0);
           int xSprite = sprite.saltar();
            Log.i("xSprite",xSprite+"");
            Log.i("xIce",spritesIce.get(0).x+"");
           int dif = xSprite - spritesIce.get(0).x;
            Log.i("dif",dif+"");
           if( dif>=-100 && dif<=100){
               Log.i("chocar","choaste");
           }
        }


        return true;

    }
}