package com.lixinjia.mycanvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.lixinjia.mycanvas.tool.Adaptation;
import com.lixinjia.mycanvas.util.SurfaceViewUtil;

/**
 * 作者：李忻佳
 * 时间：2017/6/13
 * 说明：打开红包
 */

public class OpenRedEnvelopesView extends LinearLayout{
    private Paint paint;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private int openRadius = 80;
    private Adaptation adaptation;
    private int textSize = 70;
    private String openText = "兑";
    private onTachOpen mOnTachOpen;

    public OpenRedEnvelopesView(Context context) {
        super(context);
        init(context);
    }

    public OpenRedEnvelopesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OpenRedEnvelopesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.mContext = context;
        adaptation = new Adaptation(mContext);
        setWillNotDraw(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(SurfaceViewUtil.isGradenClick((int)event.getX(),(int)event.getY(),mWidth/2,mHeight-adaptation.setCanvasAdaptation(openRadius),adaptation.setCanvasAdaptation(openRadius))){
                    if(mOnTachOpen!=null){
                        mOnTachOpen.onOpen();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#FF3333"));
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(mWidth,0);
        path.lineTo(mWidth,mHeight-mHeight/3);
        path.quadTo(mWidth/2,mHeight+adaptation.setCanvasAdaptation(openRadius),0,mHeight-mHeight/3);
        path.close();
        canvas.drawPath(path,paint);
        paint.setColor(Color.parseColor("#CF1F1F"));
        canvas.drawCircle(mWidth/2,mHeight-adaptation.setCanvasAdaptation(openRadius),adaptation.setCanvasAdaptation(openRadius),paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(adaptation.setCanvasAdaptation(textSize));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(openText,mWidth/2,mHeight-adaptation.setCanvasAdaptation(openRadius)+SurfaceViewUtil.getTextWH(openText,paint).height()/2,paint);
    }
    public void setmLierern(onTachOpen onTachOpen){
        this.mOnTachOpen = onTachOpen;
    }
    public interface onTachOpen{
        public void onOpen();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = measureHeight(heightMeasureSpec);
    }
    //根据xml的设定获取宽度
    protected int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST){

        }
        //fill_parent或者精确值
        else if (specMode == MeasureSpec.EXACTLY){

        }
        return specSize;
    }
    //根据xml的设定获取高度
    protected int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST){

        }
        //fill_parent或者精确值
        else if (specMode == MeasureSpec.EXACTLY){

        }
        return specSize;
    }
}
