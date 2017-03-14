package com.lixinjia.mycanvas.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.lixinjia.mycanvas.R;
import com.lixinjia.mycanvas.surface.MySurfaceView;

/**
 * 作者：李忻佳.
 * 时间：2016/12/24.
 * 说明：
 */

public class MyGameActivity extends Activity {
    private MySurfaceView mySurfaceView;
    private Button bulletLevelView;
    private int bulletLevel = 1;
    private Button suspend;
    private Button addRobot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_layout);
        mySurfaceView = (MySurfaceView)findViewById(R.id.mySurfaceView);
        bulletLevelView = (Button)findViewById(R.id.bulletLevel);
        suspend = (Button)findViewById(R.id.suspend);
        addRobot = (Button)findViewById(R.id.addRobot);

        bulletLevelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bulletLevel++;
                if(bulletLevel == 4){
                    bulletLevel = 1;
                }
                switch (bulletLevel) {
                    case 1:
                        bulletLevelView.setText("I");
                        break;
                    case 2:
                        bulletLevelView.setText("II");
                        break;
                    case 3:
                        bulletLevelView.setText("III");
                        break;
                }
                mySurfaceView.bulletLevel(bulletLevel);
            }
        });
        suspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(suspend.getText().toString().equals("暂停")){
                    suspend.setText("开始");
                    mySurfaceView.setSuspend(true);
                }else{
                    suspend.setText("暂停");
                    mySurfaceView.setSuspend(false);
                }
            }
        });
        addRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int robotLenght = mySurfaceView.getRobotLenght() + 1;
                if(robotLenght>4){
                    Toast.makeText(MyGameActivity.this,"机器人已上限",Toast.LENGTH_SHORT);
                    mySurfaceView.setRobotLenght(4);
                }else{
                    mySurfaceView.setRobotLenght(robotLenght);
                }
            }
        });
    }
}
