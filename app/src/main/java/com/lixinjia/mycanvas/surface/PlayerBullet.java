package com.lixinjia.mycanvas.surface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.lixinjia.mycanvas.util.SurfaceViewUtil;

/**
 * 作者：李忻佳.
 * 时间：2016/12/23.
 * 说明：主角子弹
 */

public class PlayerBullet {
    private final int screenW;
    private final Player player;
    //子弹发射角度
    private int bulletAngle;
    //隔多远发射
    private int bulletRadius;
    // 子弹图片资源
    public Bitmap bmpBullet;
    // 子弹的坐标 三个子弹级别的不同坐标
    public int bulletX, bulletY;
    // 子弹的速度
    public int speed = 5;
    // 子弹是否超屏， 优化处理
    public boolean isDead;
    //主角子弹级别
    public int bulletPlayer;
    public static final int I = 1;
    public static final int II = 2;
    public static final int III = 3;

    /**
     *
     * @param player        主角
     * @param bmpBullet     子弹Bitmap
     * @param bulletPlayer  主角子弹级别
     * @param angle         角度
     * @param bulletX
     * @param bulletY
     * @param radius        隔多远发射
     */
    public PlayerBullet(Player player, Bitmap bmpBullet, int bulletPlayer, int angle, int bulletX, int bulletY, int screenW, int radius) {
        this.player = player;
        this.bulletPlayer = bulletPlayer;
        this.bulletAngle = -angle;
        this.bulletRadius = radius;
        this.bmpBullet = bmpBullet;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.screenW = screenW;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bmpBullet, bulletX-bmpBullet.getWidth()/2, bulletY-bmpBullet.getHeight()/2, paint);
    }

    public void logic() {
        switch (bulletPlayer) {
            case PlayerBullet.I:
                speed = 15;
                break;
            case PlayerBullet.II:
                speed = 20;
                break;
            case PlayerBullet.III:
                speed = 25;
                break;
        }

        bulletRadius += speed;
        bulletX = (int) SurfaceViewUtil.getCircleCoordinatesX(player.getPlayerX(),bulletAngle,bulletRadius);
        bulletY = (int)SurfaceViewUtil.getCircleCoordinatesY(player.getPlayerY(),bulletAngle,bulletRadius);
        if (bulletRadius >screenW) {
            isDead = true;
        }
    }
}
