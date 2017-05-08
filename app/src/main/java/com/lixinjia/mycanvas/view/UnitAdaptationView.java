package com.lixinjia.mycanvas.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixinjia.mycanvas.util.JiaUtil;


/**
 * 作者：李忻佳
 * 时间：2017/5/3
 * 说明：带单位的自适应
 */

public class UnitAdaptationView extends LinearLayout {
    private Context mContext;
    private UnitAdaptationView ins;
    private int mWidth;
    private String text = "";
    private int textSize = 16;
    private int textColor = Color.BLACK;
    private String nuitText = "";
    private int nuitTextSize = 16;
    private int nuitTextColor = Color.BLACK;

    public UnitAdaptationView(Context context) {
        super(context);
        init(context);
    }

    public UnitAdaptationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UnitAdaptationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.mContext = context;
        ins = this;
        setOrientation(LinearLayout.HORIZONTAL);
    }
    private void canvase(){
        removeAllViews();
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        textView.setTextColor(textColor);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(textView,params);
        TextView nuitTextView = new TextView(mContext);
        nuitTextView.setText(nuitText);
        nuitTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, nuitTextSize);
        nuitTextView.setTextColor(nuitTextColor);
        addView(nuitTextView,params);
        int textViewWidth = JiaUtil.getViewWidth(textView);
        int nuitTextViewWidth = JiaUtil.getViewWidth(nuitTextView);
        if(textViewWidth+nuitTextViewWidth>mWidth){
            textView.setLayoutParams(new LayoutParams(mWidth-nuitTextViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setSingleLine(true);
        }
    }
    public void startCanvas(){
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mWidth = ins.getWidth();
                canvase();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setNuitText(String nuitText) {
        this.nuitText = nuitText;
    }

    public void setNuitTextSize(int nuitTextSize) {
        this.nuitTextSize = nuitTextSize;
    }

    public void setNuitTextColor(int nuitTextColor) {
        this.nuitTextColor = nuitTextColor;
    }
}
