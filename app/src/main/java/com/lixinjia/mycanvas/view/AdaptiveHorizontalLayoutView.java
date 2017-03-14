package com.lixinjia.mycanvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.LinearLayout;

/**
 * 作者：李忻佳.
 * 时间：2017/2/22.
 * 说明：自适应horizontal布局
 */

public class AdaptiveHorizontalLayoutView extends LinearLayout {
    private Context mContext;
    private int startIndex = 0;
    private int endIndex = 0;
    private Adapter adapter;
    private int mWidth;
    private AdaptiveHorizontalLayoutView ins;
    private boolean isALine = true; //是否只显示一行

    public AdaptiveHorizontalLayoutView(Context context) {
        super(context);
        init(context);
    }

    public AdaptiveHorizontalLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdaptiveHorizontalLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        mContext = context;
        ins = this;
        setOrientation(LinearLayout.VERTICAL);
        ins.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mWidth = ins.getWidth();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                updataView();
            }
        });
    }

    /**
     * 初始化view
     */
    public void updataView() {
        if(adapter!=null) {
            startIndex = 0;
            removeAllViews();
            endIndex = adapter.getCount();
            addMyView(startIndex);
        }
    }

    /**
     * 添加view
     * @param intStartIndex
     */
    private void addMyView(int intStartIndex){
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        addView(linearLayout);
        for (int i = intStartIndex; i < endIndex; i++) {
            View v = adapter.getView(i, null, null);
            //绑定Adapter的数据到LinearLayout布局
            linearLayout.addView(v);
            if(i<endIndex-1){
                int llWidth = getViewWidth(linearLayout);
                int nextIndex = i+1;
                int nextWidth = getViewWidth(adapter.getView(nextIndex, null, null));
                if(llWidth+nextWidth>mWidth){
                    if(isALine){
                        break;
                    }else{
                        startIndex = i+1;
                        addMyView(startIndex);
                    }
                    break;
                }
            }
        }
    }
    /**
     * 获取控件的宽度
     * @param view
     * @return
     */
    public static int getViewWidth(View view) {
        int width = 0;
        width = view.getWidth();
        if (width <= 0) {
            measureView(view);
            width = view.getMeasuredWidth();
        }
        return width;
    }
    /**
     * 测量角度
     * @param v
     */
    public static void measureView(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
    }

    /**
     * 设置adapter
     * @param adapter
     */
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public boolean isALine() {
        return isALine;
    }

    public void setALine(boolean ALine) {
        isALine = ALine;
    }
}
