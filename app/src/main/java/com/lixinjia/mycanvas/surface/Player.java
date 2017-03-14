package com.lixinjia.mycanvas.surface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lixinjia.mycanvas.util.SurfaceViewUtil;

/**
 * 作者：李忻佳.
 * 时间：2016/12/24.
 * 说明：主角
 */

public class Player {
    private Bitmap bitmap;
    private int playerX,playerY;
    private int jiao;
    //血量
    private int playerHp;

    public Player(Bitmap bitmap,int playerX, int playerY,int jiao,int playerHp){
        this.bitmap = bitmap;
        this.playerX = playerX;
        this.playerY = playerY;
        this.jiao = jiao;
        this.playerHp = playerHp;
    }
    public void draw(Canvas canvas, Paint paint) {
        Bitmap bitmap1 = SurfaceViewUtil.resizeAndRotateImage(bitmap,300,300,jiao);
        canvas.drawBitmap(bitmap1,playerX-bitmap1.getWidth()/2,playerY-bitmap1.getHeight()/2,paint);
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.STROKE);//设置填满
        canvas.drawText(playerHp+"",30,30,paint);
    }
    public void logic() {

    }

    // 判断碰撞(主角与敌人)
    public boolean isCollsionWith(Enemya enemya) {
        return SurfaceViewUtil.isCollsionWithRect(playerX,playerY,30,30,enemya.bulletX,enemya.bulletY,enemya.bitmap.getWidth(),enemya.bitmap.getHeight());
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int playerHp) {
        this.playerHp = playerHp;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}
