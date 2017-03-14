package com.lixinjia.mycanvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import java.util.HashMap;
import java.util.List;


/**
 * 作者：李忻佳.
 * 时间：2016/11/21.
 * 说明：形状点
 */

public class ShapePointView extends BaseDrawGridView {
    /**
     * 三角形底边长（等边三角形）
     */
    public int triangleBottomLong = 40;
    /**
     * 三角形颜色
     */
    public int triangleColor = Color.RED;
    /**
     * 正方形底边长
     */
    public int squareBottomLong = 36;
    /**
     * 正方形颜色
     */
    public int squareColor = Color.RED;
    /**
     * 圆形半径
     */
    public int circularRadius = 20;
    /**
     * 圆形颜色
     */
    public int circularColor = Color.RED;
    /**
     * 是否绘制柱体上方的数据text
     */
    private boolean dataText = false;
    /**
     * 柱体上方的数据text的颜色
     */
    private int dataTextColor = Color.parseColor("#000000");
    /**
     * 柱体上方的数据text的大小
     */
    private int dataTextSize = 30;
    /**
     * 是否绘制title右边区域
     */
    private boolean titleRight = false;
    /**
     * 设置title右边字体的大小
     */
    private int titleRightTextSize = 30;
    /**
     * 设置title右边字体的颜色
     */
    private int titleRightTextColor = Color.GRAY;
    /**
     * 设置title右边离屏幕右边距离
     */
    private int titleRightRightDistance = 30;

    private String[] XText = null;
    private int[] DataXY = null;
    private int[] TriangleData = null;
    private int[] TriangleDataColor = null;
    private int[] SquareData = null;
    private int[] SquareDataColor = null;
    private int[] CircularData = null;
    private int[] CircularDataColor = null;
    private List<HashMap<String, Object>> titleRightList;

    public ShapePointView(Context context) {
        super(context);
    }

    public ShapePointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapePointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(XText!=null){
            DataXY = calculationXCoordinate(XText);
            canvaseShapePoint();
        }
    }

    private void canvaseShapePoint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);//设置填满

        if(DataXY!=null){
            for (int i = 0; i < DataXY.length; i++) {
                //绘制三角形
                if(TriangleData!=null){
                    if(TriangleData[i]>0){
                        if(TriangleDataColor!=null&&TriangleDataColor[i]!=0){
                            mPaint.setColor(TriangleDataColor[i]);
                        }else{
                            mPaint.setColor(triangleColor);
                        }
                        Path path = new Path();
                        path.moveTo(DataXY[i]-adaptation.setCanvasAdaptation(triangleBottomLong)/2, getYValue(TriangleData[i])+adaptation.setCanvasAdaptation(triangleBottomLong)/2-adaptation.setCanvasAdaptation(3));// 此点为多边形的起点
                        path.lineTo(DataXY[i], getYValue(TriangleData[i])-adaptation.setCanvasAdaptation(triangleBottomLong)/2-adaptation.setCanvasAdaptation(3));
                        path.lineTo(DataXY[i]+adaptation.setCanvasAdaptation(triangleBottomLong)/2, getYValue(TriangleData[i])+adaptation.setCanvasAdaptation(triangleBottomLong)/2-adaptation.setCanvasAdaptation(3));
                        path.close(); // 使这些点构成封闭的多边形
                        mCanvas.drawPath(path, mPaint);
                        //绘制三角形上的text
                        if(dataText){
                            setDataText(DataXY[i],getYValue(TriangleData[i]),TriangleData[i],adaptation.setCanvasAdaptation(triangleBottomLong),dataTextColor,adaptation.setCanvasAdaptation(dataTextSize));
                        }
                    }
                }
                //绘制正方形
                if(SquareData!=null){
                    if(SquareData[i]>0){
                        if(SquareDataColor!=null&&SquareDataColor[i]!=0){
                            mPaint.setColor(SquareDataColor[i]);
                        }else{
                            mPaint.setColor(squareColor);
                        }
                        Path path = new Path();
                        path.moveTo(DataXY[i]-adaptation.setCanvasAdaptation(squareBottomLong)/2, getYValue(SquareData[i])+adaptation.setCanvasAdaptation(squareBottomLong)/2);// 此点为多边形的起点
                        path.lineTo(DataXY[i]-adaptation.setCanvasAdaptation(squareBottomLong)/2, getYValue(SquareData[i])-adaptation.setCanvasAdaptation(squareBottomLong)/2);
                        path.lineTo(DataXY[i]+adaptation.setCanvasAdaptation(squareBottomLong)/2, getYValue(SquareData[i])-adaptation.setCanvasAdaptation(squareBottomLong)/2);
                        path.lineTo(DataXY[i]+adaptation.setCanvasAdaptation(squareBottomLong)/2, getYValue(SquareData[i])+adaptation.setCanvasAdaptation(squareBottomLong)/2);
                        path.close(); // 使这些点构成封闭的多边形
                        mCanvas.drawPath(path, mPaint);
                        //绘制正方形上的text
                        if(dataText){
                            setDataText(DataXY[i],getYValue(SquareData[i]),SquareData[i],adaptation.setCanvasAdaptation(squareBottomLong),dataTextColor,adaptation.setCanvasAdaptation(dataTextSize));
                        }
                    }
                }
                //绘制圆形
                if(CircularData!=null){
                    if(CircularData[i]>0){
                        if(CircularDataColor!=null&&CircularDataColor[i]!=0){
                            mPaint.setColor(CircularDataColor[i]);
                        }else{
                            mPaint.setColor(circularColor);
                        }
                        mCanvas.drawCircle(DataXY[i], getYValue(CircularData[i]), adaptation.setCanvasAdaptation(circularRadius), mPaint);
                        //绘制圆形上的text
                        if(dataText) {
                            setDataText(DataXY[i], getYValue(CircularData[i]), CircularData[i], adaptation.setCanvasAdaptation(circularRadius), dataTextColor, adaptation.setCanvasAdaptation(dataTextSize));
                        }
                    }
                }
            }
        }
        if(titleRight){
            if(titleRightList!=null){
                int titleRightRightDistanceStart = getmWidth() - adaptation.setCanvasAdaptation(titleRightRightDistance);
                for (HashMap<String, Object> stringObjectHashMap : titleRightList) {
                    String text = String.valueOf(stringObjectHashMap.get("text"));
                    String type = String.valueOf(stringObjectHashMap.get("type"));//1 圆形 2 正方形 3 三角形
                    int color = (Integer)stringObjectHashMap.get("color");
                    mPaint = new Paint();
                    mPaint.setAntiAlias(true);
                    mPaint.setTextSize(titleRightTextSize);
                    mPaint.setColor(titleRightTextColor);
                    titleRightRightDistanceStart -= getTextWH(text,mPaint).width();
                    mCanvas.drawText(text, adaptation.setCanvasAdaptation(titleRightRightDistanceStart),getTitleHeight()/2+adaptation.setCanvasAdaptation(10),mPaint);
                    titleRightRightDistanceStart -= adaptation.setCanvasAdaptation(20);

                    mPaint.setStyle(Paint.Style.FILL);//设置填满
                    mPaint.setColor(color);
                    switch (type) {
                        case "1":
                            mCanvas.drawCircle(titleRightRightDistanceStart, getTitleHeight()/2, adaptation.setCanvasAdaptation(circularRadius), mPaint);
                            break;
                        case "2":
                            Path path = new Path();
                            path.moveTo(titleRightRightDistanceStart-adaptation.setCanvasAdaptation(squareBottomLong)/2, getTitleHeight()/2+adaptation.setCanvasAdaptation(squareBottomLong)/2);// 此点为多边形的起点
                            path.lineTo(titleRightRightDistanceStart-adaptation.setCanvasAdaptation(squareBottomLong)/2, getTitleHeight()/2-adaptation.setCanvasAdaptation(squareBottomLong)/2);
                            path.lineTo(titleRightRightDistanceStart+adaptation.setCanvasAdaptation(squareBottomLong)/2, getTitleHeight()/2-adaptation.setCanvasAdaptation(squareBottomLong)/2);
                            path.lineTo(titleRightRightDistanceStart+adaptation.setCanvasAdaptation(squareBottomLong)/2, getTitleHeight()/2+adaptation.setCanvasAdaptation(squareBottomLong)/2);
                            path.close(); // 使这些点构成封闭的多边形
                            mCanvas.drawPath(path, mPaint);
                            break;
                        case "3":

                            Path path2 = new Path();
                            path2.moveTo(titleRightRightDistanceStart-adaptation.setCanvasAdaptation(triangleBottomLong)/2, getTitleHeight()/2+adaptation.setCanvasAdaptation(triangleBottomLong)/2-adaptation.setCanvasAdaptation(3));// 此点为多边形的起点
                            path2.lineTo(titleRightRightDistanceStart, getTitleHeight()/2-adaptation.setCanvasAdaptation(triangleBottomLong)/2-adaptation.setCanvasAdaptation(3));
                            path2.lineTo(titleRightRightDistanceStart+adaptation.setCanvasAdaptation(triangleBottomLong)/2, getTitleHeight()/2+adaptation.setCanvasAdaptation(triangleBottomLong)/2-adaptation.setCanvasAdaptation(3));
                            path2.close(); // 使这些点构成封闭的多边形
                            mCanvas.drawPath(path2, mPaint);
                            break;
                    }
                    titleRightRightDistanceStart -= adaptation.setCanvasAdaptation(40);
                }
            }
        }
    }
    /**
     * 设置title右边的数据
     * @param titleRightList
     */
    public void setTitleRightData(List<HashMap<String, Object>> titleRightList){
        this.titleRightList = titleRightList;
    }
    /**
     * 设置圆形数据
     */
    public void setCircularData(int[] data){
        CircularData = data;
    }
    /**
     * 设置圆形数据的颜色
     */
    public void setCircularDataColor(int[] color){
        CircularDataColor = color;
    }
    /**
     * 设置正方形数据
     */
    public void setSquareData(int[] data){
        SquareData = data;
    }
    /**
     * 设置正方形数据的颜色
     */
    public void setSquareDataColor(int[] color){
        SquareDataColor = color;
    }
    /**
     * 设置三角形数据
     */
    public void setTriangleData(int[] data){
        TriangleData = data;
    }
    /**
     * 设置三角形数据的颜色
     */
    public void setTriangleDataColor(int[] color){
        TriangleDataColor = color;
    }

    /**
     * 设置X轴的text
     * @param text
     */
    public void setXText(String[] text){
        XText = text;
    }

    public int getTriangleBottomLong() {
        return triangleBottomLong;
    }

    public void setTriangleBottomLong(int triangleBottomLong) {
        this.triangleBottomLong = triangleBottomLong;
    }

    public int getTriangleColor() {
        return triangleColor;
    }

    public void setTriangleColor(int triangleColor) {
        this.triangleColor = triangleColor;
    }

    public int getSquareBottomLong() {
        return squareBottomLong;
    }

    public void setSquareBottomLong(int squareBottomLong) {
        this.squareBottomLong = squareBottomLong;
    }

    public int getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(int squareColor) {
        this.squareColor = squareColor;
    }

    public int getCircularRadius() {
        return circularRadius;
    }

    public void setCircularRadius(int circularRadius) {
        this.circularRadius = circularRadius;
    }

    public int getCircularColor() {
        return circularColor;
    }

    public void setCircularColor(int circularColor) {
        this.circularColor = circularColor;
    }

    public boolean isDataText() {
        return dataText;
    }

    public void setDataText(boolean dataText) {
        this.dataText = dataText;
    }

    public int getDataTextColor() {
        return dataTextColor;
    }

    public void setDataTextColor(int dataTextColor) {
        this.dataTextColor = dataTextColor;
    }

    public int getDataTextSize() {
        return dataTextSize;
    }

    public void setDataTextSize(int dataTextSize) {
        this.dataTextSize = dataTextSize;
    }

    public boolean isTitleRight() {
        return titleRight;
    }

    public void setTitleRight(boolean titleRight) {
        this.titleRight = titleRight;
    }

    public int getTitleRightTextSize() {
        return titleRightTextSize;
    }

    public void setTitleRightTextSize(int titleRightTextSize) {
        this.titleRightTextSize = titleRightTextSize;
    }

    public int getTitleRightTextColor() {
        return titleRightTextColor;
    }

    public void setTitleRightTextColor(int titleRightTextColor) {
        this.titleRightTextColor = titleRightTextColor;
    }

    public int getTitleRightRightDistance() {
        return titleRightRightDistance;
    }

    public void setTitleRightRightDistance(int titleRightRightDistance) {
        this.titleRightRightDistance = titleRightRightDistance;
    }
}
