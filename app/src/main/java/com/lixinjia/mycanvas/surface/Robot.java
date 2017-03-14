package com.lixinjia.mycanvas.surface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.lixinjia.mycanvas.util.SurfaceViewUtil;

import java.util.Vector;

/**
 * 作者：李忻佳.
 * 时间：2016/12/26.
 * 说明：机器人
 */

public class Robot {
    private final Bitmap bitmapRobot;
    public final int robotX;
    public final int robotY;
    private final Bitmap bitmapRobotBullet;
    public final int screenw;
    //射程
    private int range = 100;
    //角度
    public int jiao = 180;

    private int countRobotBullet = 0;
    //机器人子弹级别
    public final int I = 1;
    public final int II = 2;
    public final int III = 3;
    public int bulletRobot = I;

    //机器人子弹容器实例
    private Vector<RobotBullet> vcRobotBullet;

    public Robot(Bitmap bitmapRobot, Bitmap bitmapRobotBullet, int robotX, int robotY, int screenw){
        this.bitmapRobot = bitmapRobot;
        this.bitmapRobotBullet = bitmapRobotBullet;
        this.robotX = robotX;
        this.robotY = robotY;
        this.screenw = screenw;

        //机器人子弹容器实例
        vcRobotBullet = new Vector<RobotBullet>();
    }

    public void draw(Canvas canvas, Paint paint) {
        Bitmap bitmap1 = SurfaceViewUtil.resizeAndRotateImage(bitmapRobot,150,150,jiao);
        canvas.drawBitmap(bitmap1, robotX-bitmapRobot.getWidth()/2, robotY-bitmapRobot.getHeight()/2, paint);
    }

    public void logic(Enemya enemya) {
//        if(isRange(enemya)){
//            jiao = SurfaceViewUtil.getRotationAngle(robotX,robotY,enemya.bulletX,enemya.bulletY);
//            //添加子弹
//            countRobotBullet++;
//            if (countRobotBullet % 5 == 0) {
//                switch (bulletRobot) {
//                    case I:
//                        vcRobotBullet.add(new RobotBullet(Robot.this,enemya,bitmapRobotBullet,bitmapRobot.getWidth()/2));
//                        break;
//                    case PlayerBullet.II:
//                        vcRobotBullet.add(new RobotBullet(Robot.this,enemya,bitmapRobotBullet,bitmapRobot.getWidth()/2));
//                        vcRobotBullet.add(new RobotBullet(Robot.this,enemya,bitmapRobotBullet,bitmapRobot.getWidth()/2));
//                        break;
//                    case PlayerBullet.III:
//                        vcRobotBullet.add(new RobotBullet(Robot.this,enemya,bitmapRobotBullet,bitmapRobot.getWidth()/2));
//                        vcRobotBullet.add(new RobotBullet(Robot.this,enemya,bitmapRobotBullet,bitmapRobot.getWidth()/2));
//                        vcRobotBullet.add(new RobotBullet(Robot.this,enemya,bitmapRobotBullet,bitmapRobot.getWidth()/2));
//                        break;
//                }
//            }
//        }
    }

    public boolean isRange(Enemya enemya){
        if(SurfaceViewUtil.isCollisionWithCircle(robotX,enemya.bulletX,robotY,enemya.bulletY,range,enemya.bitmap.getWidth()/2)){
            return true;
        }
        return false;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
