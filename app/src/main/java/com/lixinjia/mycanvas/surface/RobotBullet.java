package com.lixinjia.mycanvas.surface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lixinjia.mycanvas.util.SurfaceViewUtil;

/**
 * 作者：李忻佳.
 * 时间：2016/12/26.
 * 说明：机器人子弹
 */

public class RobotBullet {
    private final Robot robot;
    private final Enemya enemya;
    private int radius;
    //子弹图
    private final Bitmap bitmapRobotBullet;
    // 子弹的坐标 三个子弹级别的不同坐标
    public int bulletX, bulletY;
    public final int I = 1;
    public final int II = 2;
    public final int III = 3;
    //子弹速度
    private int speed = 10;
    // 子弹是否超屏， 优化处理
    public boolean isDead;

    public RobotBullet(Robot robot,Enemya enemya, Bitmap bitmapRobotBullet, int radius){
        this.robot = robot;
        this.enemya = enemya;
        this.bitmapRobotBullet = bitmapRobotBullet;
        this.radius = radius;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmapRobotBullet, bulletX-bitmapRobotBullet.getWidth()/2, bulletY-bitmapRobotBullet.getHeight()/2, paint);
    }

    public void logic() {
        switch (robot.bulletRobot) {
            case I:
                speed = 15;
                break;
            case II:
                speed = 20;
                break;
            case III:
                speed = 25;
                break;
        }
        radius += speed;
        bulletX = (int) SurfaceViewUtil.getCircleCoordinatesX(enemya.bulletX,robot.jiao,radius);
        bulletY = (int)SurfaceViewUtil.getCircleCoordinatesY(enemya.bulletY,robot.jiao,radius);
        if (radius >robot.screenw) {
            isDead = true;
        }
    }
}
