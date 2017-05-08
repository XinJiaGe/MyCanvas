package com.lixinjia.mycanvas.util;

import android.view.View;

/**
 * 作者：李忻佳.
 * 时间：2017/2/10.
 * 说明：
 */

public class JiaUtil {
    /**
     * 去掉小数点
     * @param str
     * @return
     */
    public static String deleteDecimal(String str){
        try {
            int intstr = Integer.parseInt(str);
            double doublestr = Double.parseDouble(str);
            if(doublestr>intstr){
                return str;
            }else{
                return intstr+"";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return str;
        }
    }
    /**
     * 去掉小数点
     * @param str
     * @return
     */
    public static String deleteDecimal(double str){
        try {
            int intstr = (int)str;
            if(str>intstr){
                return str+"";
            }else{
                return intstr+"";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return str+"";
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
}
