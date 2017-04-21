package com.lixinjia.mycanvas.util;

/**
 * 作者：李忻佳
 * 时间：2017/4/21
 * 说明：小数点
 */

public class DecimalUtils {
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
}
