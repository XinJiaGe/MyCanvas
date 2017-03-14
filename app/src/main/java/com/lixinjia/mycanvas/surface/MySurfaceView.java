package com.lixinjia.mycanvas.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import com.lixinjia.mycanvas.R;
import com.lixinjia.mycanvas.util.SurfaceViewUtil;

import java.util.Vector;

/**
 * 作者：李忻佳.
 * 时间：2016/12/21.
 * 说明：
 */

public class MySurfaceView extends BaseDrawSurfaceView{
    //主角坐标X
    private int playerX;
    //主角坐标Y
    private int playerY;
    //主角发射角度
    private int playerJiao = 180;
    //主角
    private Bitmap bitmapPlayer;
    //子弹
    private Bitmap bitmapBullet;
    // 主角子弹容器
    private Vector<PlayerBullet> vcPlayerBullet;
    // 添加主角子弹的计数器
    private int countPlayerBullet;
    // 添加机器人1子弹的计数器
    private int countRobot1Bullet;
    // 添加机器人2子弹的计数器
    private int countRobot2Bullet;
    // 添加机器人3子弹的计数器
    private int countRobot3Bullet;
    // 添加机器人4子弹的计数器
    private int countRobot4Bullet;
    //主角子弹级别
    public int bulletPlayer = PlayerBullet.I;
    //敌人
    private Bitmap bitmapEnemy;
    //敌人容器实例
    private Vector<Enemya> vcEnemya;
    // 添加敌人的计数器
    private int countEnemya;
    //主角
    private Player player;
    //主角血量
    private int playerHp = 100;
    //暂停
    private boolean suspend = false;
    //机器人1
    private Bitmap bitmapRobot1;
    //机器人2
    private Bitmap bitmapRobot2;
    //机器人3
    private Bitmap bitmapRobot3;
    //机器人4
    private Bitmap bitmapRobot4;
    //机器人容器实例
    private Vector<Robot> vcRobot;
    //机器人个数
    private int robotLenght = 0;

    //机器人位置坐标
    private int robot1X;
    private int robot1Y;
    private int robot2X;
    private int robot2Y;
    private int robot3X;
    private int robot3Y;
    private int robot4X;
    private int robot4Y;
    //机器人
    private Robot robot1;
    private Robot robot2;
    private Robot robot3;
    private Robot robot4;


    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //敌人
        bitmapEnemy = BitmapFactory.decodeResource(getResources(), R.drawable.ic_enemya);
        bitmapEnemy = SurfaceViewUtil.zoomBitmap(bitmapEnemy,40,40);
        //主角
        bitmapPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        //机器人1
        bitmapRobot1 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        //机器人2
        bitmapRobot2 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        //机器人3
        bitmapRobot3 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        //机器人4
        bitmapRobot4 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        //主角
        bitmapPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        //子弹
        bitmapBullet = BitmapFactory.decodeResource(getResources(), R.drawable.ic_main_dot2_foused);
        bitmapBullet = SurfaceViewUtil.zoomBitmap(bitmapBullet,20,20);
        //主角子弹容器实例
        vcPlayerBullet = new Vector<PlayerBullet>();
        //敌人容器实例
        vcEnemya = new Vector<Enemya>();
        super.surfaceCreated(surfaceHolder);
        //设置主角位置
        playerX = getScreenW()/2;
        playerY = getScreenH()/2;
        robot1X = 0;
        robot1Y = 0;
        robot2X = playerX+100;
        robot2Y = playerY-100;
        robot3X = playerX-100;
        robot3Y = playerY+100;
        robot4X = playerX+100;
        robot4Y = playerY+100;
    }

    @Override
    protected void logic() {
        super.logic();
        if(!suspend) {
            //添加子弹
            countPlayerBullet++;
            if (countPlayerBullet % 5 == 0) {
                switch (bulletPlayer) {
                    case PlayerBullet.I:
                        vcPlayerBullet.add(new PlayerBullet(player, bitmapBullet, bulletPlayer, playerJiao, playerX, playerY, getScreenW(), 150));
                        break;
                    case PlayerBullet.II:
                        vcPlayerBullet.add(new PlayerBullet(player, bitmapBullet, bulletPlayer, playerJiao - 2, playerX, playerY, getScreenW(), 150));
                        vcPlayerBullet.add(new PlayerBullet(player, bitmapBullet, bulletPlayer, playerJiao + 2, playerX, playerY, getScreenW(), 150));
                        break;
                    case PlayerBullet.III:
                        vcPlayerBullet.add(new PlayerBullet(player, bitmapBullet, bulletPlayer, playerJiao, playerX, playerY, getScreenW(), 150));
                        vcPlayerBullet.add(new PlayerBullet(player, bitmapBullet, bulletPlayer, playerJiao - 4, playerX, playerY, getScreenW(), 150));
                        vcPlayerBullet.add(new PlayerBullet(player, bitmapBullet, bulletPlayer, playerJiao + 4, playerX, playerY, getScreenW(), 150));
                        break;
                }
            }

            //添加敌人
            countEnemya++;
            if (countEnemya % 20 == 0) {
                vcEnemya.add(new Enemya(bitmapEnemy, playerX, playerY, getScreenW(), getScreenH()));
            }

            //处理主角子弹逻辑
            for (int i = 0; i < vcPlayerBullet.size(); i++) {
                PlayerBullet b = vcPlayerBullet.elementAt(i);
                for (int i1 = 0; i1 < vcEnemya.size(); i1++) {
                    Enemya enemya = vcEnemya.get(i1);
                    //判断主角子弹是否和敌人碰撞
                    if (SurfaceViewUtil.isCollsionWithRect(b.bulletX, b.bulletY, b.bmpBullet.getWidth(), b.bmpBullet.getHeight(), enemya.bulletX, enemya.bulletY, enemya.bitmap.getWidth(), enemya.bitmap.getHeight())) {
                        vcPlayerBullet.removeElement(b);
                        vcEnemya.removeElement(vcEnemya.get(i1));
                    }
                }
                if (b.isDead) {
                    vcPlayerBullet.removeElement(b);
                } else {
                    b.logic();
                }
            }
            //处理敌人逻辑
            for (int i = 0; i < vcEnemya.size(); i++) {
                Enemya b = vcEnemya.elementAt(i);
                //判断敌人是否与主角碰撞
                if (b.isDead || player.isCollsionWith(b)) {
                    // 处理敌机与主角的碰撞
                    vcEnemya.removeElement(b);
                    // 发生碰撞，主角血量-1
                    playerHp--;
                    // 当主角血量小于0，判定游戏失败
                    if (playerHp <= -1) {
//                    gameState = GAME_LOST;
                    }
                }else {
                    b.logic();
                }
                try {
                    switch (robotLenght) {
                        case 1:
                            robot1.logic(b);
                            break;
                        case 2:
                            robot1.logic(b);
                            robot2.logic(b);
                            break;
                        case 3:
                            robot1.logic(b);
                            robot2.logic(b);
                            robot3.logic(b);
                            break;
                        case 4:
                            robot1.logic(b);
                            robot2.logic(b);
                            robot3.logic(b);
                            robot4.logic(b);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onTouchDown(float rawX, float rawY) {
        super.onTouchDown(rawX, rawY);
        playerJiao = SurfaceViewUtil.getRotationAngle(getScreenW()/2,getScreenH()/2,(int)rawX,(int)rawY);
    }

    @Override
    protected void onTouchMove(float rawX, float rawY) {
        super.onTouchMove(rawX, rawY);
        playerJiao = SurfaceViewUtil.getRotationAngle(getScreenW()/2,getScreenH()/2,(int)rawX,(int)rawY);
    }

    @Override
    protected void onTouchUp(float rawX, float rawY) {
        super.onTouchUp(rawX, rawY);
    }

    @Override
    protected void myDraw() {
        super.myDraw();
        if(!suspend) {
            //刷屏，画布白色
            mCanvas.drawColor(Color.WHITE);
            //绘制主角
            player = new Player(bitmapPlayer, playerX, playerY, playerJiao, playerHp);
            player.draw(mCanvas, mPaint);

            //绘制机器人
            switch (robotLenght) {
                case 1:
                    robot1 = new Robot(bitmapRobot1,bitmapBullet, robot1X, robot1Y,getScreenW());
                    robot1.draw(mCanvas, mPaint);
                    break;
                case 2:
                    robot1 = new Robot(bitmapRobot1,bitmapBullet, robot1X, robot1Y,getScreenW());
                    robot2 = new Robot(bitmapRobot2,bitmapBullet, robot2X, robot2Y,getScreenW());
                    robot1.draw(mCanvas, mPaint);
                    robot2.draw(mCanvas, mPaint);
                    break;
                case 3:
                    robot1 = new Robot(bitmapRobot1,bitmapBullet, robot1X, robot1Y,getScreenW());
                    robot2 = new Robot(bitmapRobot2,bitmapBullet, robot2X, robot2Y,getScreenW());
                    robot3 = new Robot(bitmapRobot3,bitmapBullet, robot3X, robot3Y,getScreenW());
                    robot1.draw(mCanvas, mPaint);
                    robot2.draw(mCanvas, mPaint);
                    robot3.draw(mCanvas, mPaint);
                    break;
                case 4:
                    robot1 = new Robot(bitmapRobot1,bitmapBullet, robot1X, robot1Y,getScreenW());
                    robot2 = new Robot(bitmapRobot2,bitmapBullet, robot2X, robot2Y,getScreenW());
                    robot3 = new Robot(bitmapRobot3,bitmapBullet, robot3X, robot3Y,getScreenW());
                    robot4 = new Robot(bitmapRobot4,bitmapBullet, robot4X, robot4Y,getScreenW());
                    robot1.draw(mCanvas, mPaint);
                    robot2.draw(mCanvas, mPaint);
                    robot3.draw(mCanvas, mPaint);
                    robot4.draw(mCanvas, mPaint);
                    break;
            }

            // 处理主角子弹绘制
            for (int i = 0; i < vcPlayerBullet.size(); i++) {
                vcPlayerBullet.elementAt(i).draw(mCanvas, mPaint);
            }
            // 处理敌人绘制
            for (int i = 0; i < vcEnemya.size(); i++) {
                vcEnemya.elementAt(i).draw(mCanvas, mPaint);
            }
        }
    }

    /**
     * 设置主角子弹级别
     * @param level
     */
    public void bulletLevel(int level){
        switch (level) {
            case 1:
                bulletPlayer = PlayerBullet.I;
                break;
            case 2:
                bulletPlayer = PlayerBullet.II;
                break;
            case 3:
                bulletPlayer = PlayerBullet.III;
                break;
        }
    }

    public boolean isSuspend() {
        return suspend;
    }

    public void setSuspend(boolean suspend) {
        this.suspend = suspend;
    }

    public int getRobotLenght() {
        return robotLenght;
    }

    public void setRobotLenght(int robotLenght) {
        this.robotLenght = robotLenght;
    }
}
