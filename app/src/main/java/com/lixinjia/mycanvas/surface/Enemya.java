package com.lixinjia.mycanvas.surface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lixinjia.mycanvas.util.SurfaceViewUtil;

/**
 * 作者：李忻佳.
 * 时间：2016/12/24.
 * 说明：敌人
 */

public class Enemya {
    private int playerX,playerY;

    private int screenW;
    public Bitmap bitmap;
    //跑出边界是否删除
    public boolean isDead;
    //敌人方位
    public int enemyPosition = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    //敌人坐标
    public int bulletX,bulletY;
    //敌人角度
    private int enemyJiao = 180;
    //敌人的速度
    private int speed = 10;
    //敌人距离主角距离
    private int bulletRadius;
    //敌人高度
    private int enemyaHeight;

    public Enemya(Bitmap bitmap, int playerX,int playerY, int screenW, int screenH){
        this.bitmap = bitmap;
        this.playerX = playerX;
        this.playerY = playerY;
        this.screenW = screenW;
        //随机生成敌人高度
        this.enemyaHeight = SurfaceViewUtil.getRandomEnd(screenH);
        //随机生成敌人在左边还是右边
        this.enemyPosition = SurfaceViewUtil.getRandomEnd(2);
        if(playerX>screenW/2){
            this.bulletRadius = playerX+30;
        }else{
            this.bulletRadius = screenW-playerX+30;
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setAlpha(100);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//设置填满
        canvas.drawCircle(bulletX, bulletY, bitmap.getWidth()/2, paint);
        canvas.drawBitmap(bitmap, bulletX-bitmap.getWidth()/2, bulletY-bitmap.getHeight()/2, paint);
    }
    public void logic() {
        bulletRadius -= speed;
        switch (enemyPosition) {
            case LEFT:
                enemyJiao = SurfaceViewUtil.getRotationAngle(playerX,playerY,0,enemyaHeight);
                break;
            case RIGHT:
                enemyJiao = SurfaceViewUtil.getRotationAngle(playerX,playerY,screenW,enemyaHeight);
                break;
        }
        bulletX = (int) SurfaceViewUtil.getCircleCoordinatesX(playerX,enemyJiao,bulletRadius);
        bulletY = (int)SurfaceViewUtil.getCircleCoordinatesY(playerY,enemyJiao,bulletRadius);
        if (bulletRadius >screenW) {
            isDead = true;
        }
    }
}
