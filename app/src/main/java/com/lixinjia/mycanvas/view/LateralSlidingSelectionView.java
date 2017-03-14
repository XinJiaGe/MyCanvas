package com.lixinjia.mycanvas.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;

import com.lixinjia.mycanvas.tool.Adaptation;


/**
 * 作者：李忻佳.
 * 时间：2016/12/13.
 * 说明：横向滑动选择
 */

public class LateralSlidingSelectionView extends LinearLayout {
    /**
     * text字体大小
     */
    private int textSize = 17;
    /**
     * text选中字体大小
     */
    private int textSelectedSize = 18;
    /**
     * text字体颜色
     */
    private int textColor = Color.GRAY;
    /**
     * text选中字体颜色
     */
    private int textSelectedColor = Color.parseColor("#43b2e6");
    /**
     * 每个词的宽度
     */
    private int textWeight = 200;
    /**
     * 初始化选中index
     */
    private int mIndex = 0;
    private int lastIndex = -1;
    private Adaptation mAdaptation;
    private OverScroller mOverScroller;
    private float lastX;
    private float lastY;
    private int mViewWidth;
    private int mWidth;
    private int mHeight;
    private int mStartX = 0;
    private String[] mText;
    private Context mContext;
    private float disX;
    private LateralSlidingSelectionViewListener mOnListener;
    private int[] textViewId;
    private LinearLayout linearLayout;
    private int indexIn = mIndex;
    private int indexInto = mIndex;

    public LateralSlidingSelectionView(Context context) {
        super(context);
        init(context);
    }

    public LateralSlidingSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LateralSlidingSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void setText(String[] text){
        this.mText = text;
        if(mText!=null){
            addViews();
        }
    }
    private void init(Context context) {
        mContext = context;
        mAdaptation = new Adaptation(context);
        mOverScroller = new OverScroller(getContext());
        setOverScrollMode(OVER_SCROLL_ALWAYS);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
    }
    public void setMyOnListener(LateralSlidingSelectionViewListener onListener){
        this.mOnListener = onListener;
    }

    /**
     * 变换字体
     * @param index
     */
    private void changeText(int index){
        for (int i = 0; i < textViewId.length; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i).findViewById(textViewId[i]);
            if(i == index){
//                textView.setTextSize(mAdaptation.setCanvasAdaptation(textSelectedSize));
                textView.setTextColor(textSelectedColor);
            }else{
//                textView.setTextSize(mAdaptation.setCanvasAdaptation(textSize));
                textView.setTextColor(textColor);
            }
        }
    }
    /**
     * 初始谁对应中间
     * @param index
     */
    private void setStartIndex(int index){
        if(mText!=null&&textViewId!=null){
            if(index>=0){
                mIndex = index;
                if(index != lastIndex){
                    if(mOnListener!=null) {
                        mOnListener.click(index);
                    }
                }
                lastIndex = index;
                changeText(index);
                mStartX -= mViewWidth/mText.length/2+mViewWidth/mText.length*index;
                smoothScrollTo(-mStartX);
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                disX = event.getRawX() - lastX;
                smoothScrollTo(-(int)disX-mStartX);

                //判断左边是否滑过了
                if(mOverScroller.getFinalX()-mViewWidth/mText.length/2<-(mViewWidth/2)){
                    indexIn = 0;
                }else {
                    indexIn = Math.abs(Math.round((int)(disX / (float) (mViewWidth / mText.length)))-mIndex);
                    if (indexIn >= mText.length) {
                        indexIn = mText.length - 1;
                    }
                }
                if(indexIn != indexInto){
                    indexInto = indexIn;
                    changeText(indexIn);
                    mOnListener.slideIn(true, indexIn);
                }
                break;
            case MotionEvent.ACTION_UP:
                //判断左边是否滑过了
                if(mOverScroller.getFinalX()-mViewWidth/mText.length/2<-(mViewWidth/2)){
                    mStartX = mViewWidth/2;
                    //定位到第一个
                    setStartIndex(0);
                }else{
                    //判断滑动是否超过10
                    if(Math.abs(disX) > 10){
                        mStartX = mViewWidth/2;
                        //计算
                        float lenght2 = Math.abs(disX)/(float)(mViewWidth/mText.length);
                        if(disX>0){
                            mIndex -= Math.round(lenght2);
                        }else{
                            mIndex += Math.round(lenght2);
                        }
                        //判断右边是否划过了
                        if(mIndex>=mText.length){
                            mIndex = mText.length-1;
                        }
                        setStartIndex(mIndex);
                    }
                }
                if(mOnListener!=null) {
                    mOnListener.slideIn(false, mIndex);
                }
                break;
        }

        return true;
    }
    //添加view
    private void addViews(){
        mIndex = 0;
        removeAllViews();
        textViewId = new int[mText.length];
        linearLayout = new LinearLayout(mContext);
        LayoutParams linearLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        for (int i = 0; i < mText.length; i++) {
            TextView textView = new TextView(mContext);
            LayoutParams params = new LayoutParams(mAdaptation.setCanvasAdaptation(textWeight), LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(params);
            textView.setText(mText[i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(mAdaptation.setCanvasAdaptation(textSize));
            final int finalI = i;
            textView.setId(i+10);
            textViewId[i] = textView.getId();
            textView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            lastX = event.getRawX();
                            lastY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            disX = event.getRawX() - lastX;
                            smoothScrollTo(-(int)disX-mStartX);

                            //判断左边是否滑过了
                            if(mOverScroller.getFinalX()-mViewWidth/mText.length/2<-(mViewWidth/2)){
                                indexIn = 0;
                            }else {
                                indexIn = Math.abs(Math.round((int)(disX / (float) (mViewWidth / mText.length)))-mIndex);
                                if (indexIn >= mText.length) {
                                    indexIn = mText.length - 1;
                                }
                            }
                            if(indexIn != indexInto){
                                indexInto = indexIn;
                                changeText(indexIn);
                                mOnListener.slideIn(true, indexIn);
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            //判断左边是否滑过了
                            if(mOverScroller.getFinalX()-mViewWidth/mText.length/2<-(mViewWidth/2)){
                                mStartX = mViewWidth/2;
                                //定位到第一个
                                setStartIndex(0);
                            }else{
                                //判断滑动是否超过10
                                if(Math.abs(disX) > 10){
                                    mStartX = mViewWidth/2;
                                    //计算
                                    float lenght = Math.abs(disX)/(float)(mViewWidth/mText.length);
                                    if(disX>0){
                                        mIndex -= Math.round(lenght);
                                    }else{
                                        mIndex += Math.round(lenght);
                                    }
                                    //判断右边是否划过了
                                    if(mIndex>=mText.length){
                                        mIndex = mText.length-1;
                                    }
                                    setStartIndex(mIndex);
                                }else{
                                    mStartX = mViewWidth/2;
                                    mIndex = finalI;
                                    setStartIndex(finalI);
                                }
                            }
                            if(mOnListener!=null) {
                                mOnListener.slideIn(false, mIndex);
                            }
                            break;
                    }
                    return true;
                }
            });
            linearLayout.addView(textView);
        }
        addView(linearLayout);
        mViewWidth = getWidths(linearLayout);
    }
    //获取view 的宽度
    private int getWidths(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx) {
        int dx = fx - mOverScroller.getFinalX();
        smoothScrollBy(dx, mOverScroller.getFinalY());
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {
        //设置mScroller的滚动偏移量
        mOverScroller.startScroll(mOverScroller.getFinalX(), mOverScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (mOverScroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = measureHeight(heightMeasureSpec);
        mStartX = mViewWidth/2;
        setStartIndex(mIndex);
    }

    //根据xml的设定获取宽度
    private int measureWidth(int measureSpec) {
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
    private int measureHeight(int measureSpec) {
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
    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextSelectedSize() {
        return textSelectedSize;
    }

    public void setTextSelectedSize(int textSelectedSize) {
        this.textSelectedSize = textSelectedSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSelectedColor() {
        return textSelectedColor;
    }

    public void setTextSelectedColor(int textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
    }

    public int getTextWeight() {
        return textWeight;
    }

    public void setTextWeight(int textWeight) {
        this.textWeight = textWeight;
    }
}
