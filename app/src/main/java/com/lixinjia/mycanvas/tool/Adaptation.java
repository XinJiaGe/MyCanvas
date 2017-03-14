package com.lixinjia.mycanvas.tool;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.HashMap;

/**
 * 作者：李忻佳.
 * 时间：2016/11/18.
 * 说明：工具
 */

public class Adaptation {
    private final Context mContext;
    private Adaptation adaptation;
    public int screenWidth;
    public int screenHeight;
    private float RATIO;

    public Adaptation(Context context){
        this.mContext = context;
        init();
    }
    private void init(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        float ratioWidth = (float)screenWidth / 1080;
        float ratioHeight = (float)screenHeight / 1920;
        RATIO = Math.min(ratioWidth, ratioHeight);
    }
    /**
     * 用于适配不同分辨率的值
     * @param value   值
     * @return
     */
    public int setCanvasAdaptation(int value){
        int a = Math.round(value * RATIO);
        return a;
    }

    /**
     * 获取数组里面的最大值和最小值
     * @param value
     * @return
     */
    public HashMap<String, Integer> getValueMaxMin(int[] value){
        int i,min,max;
        HashMap<String ,Integer> map = new HashMap<>();
        min=max=value[0];
        for(i=0;i<value.length;i++){
            if(value[i]>max)   // 判断最大值
                max=value[i];
            if(value[i]<min)   // 判断最小值
                min=value[i];
        }
        map.put("max",max);
        map.put("min",min);
        return map;
    }
    /**
     * 获取数组里面的最大值和最小值
     * @param value String
     * @return
     */
    public HashMap<String, Double> getValueMaxMin(String[] value){
        Double min,max;
        HashMap<String ,Double> map = new HashMap<>();
        min=max= Double.valueOf(value[0].equals("")?"0":value[0]);
        for(int i=0;i<value.length;i++)
        {
            if(Double.valueOf(value[i].equals("")?"0":value[i])>max)   // 判断最大值
                max= Double.valueOf(value[i].equals("")?"0":value[i]);
            if(Double.valueOf(value[i].equals("")?"0":value[i])<min)   // 判断最小值
                min= Double.valueOf(value[i].equals("")?"0":value[i]);
        }
        map.put("max",max);
        map.put("min",min);
        return map;
    }

    /**
     * 获取数组里面的最大值的大值
     * @param value double
     * @return
     */
    public int getValueMaxLargeValue(double value){
        int intValue = new Double(value).intValue();
        if(intValue>9){
            String strvalue = String.valueOf(Integer.parseInt(String.valueOf(intValue).substring(0,1))+1);
            for (int i = 0; i < String.valueOf(intValue).length()-1; i++) {
                strvalue += "0";
            }
            return Integer.parseInt(strvalue);
        }else{
            return intValue+1;
        }
    }
}
