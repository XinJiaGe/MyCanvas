package com.lixinjia.mycanvas;

import com.alibaba.fastjson.JSON;
import com.lixinjia.mycanvas.model.Cart_checkActModel;
import com.lixinjia.mycanvas.utile.CharacterParser;

import org.junit.Test;

import java.util.HashMap;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        System.out.println(getJiao(100,100,300,200));
//        System.out.println(getJiao(100,100,300,600));
//        System.out.println(getJiao(100,100,-200,600));
//        System.out.println(getJiao(100,100,200,-1000));
        String name = "亲子鉴定";
        String fpy = "qzjd";
        String key = "亲在";
//        String cp = "hz";
//        System.out.println(name.indexOf(key.toLowerCase()) != -1);
//        System.out.println(name.startsWith(key.toLowerCase()));
        System.out.println(name.indexOf(key.toLowerCase()) != -1 || CharacterParser.getLetters(name).startsWith(key.toLowerCase()) || fpy.startsWith(key.toLowerCase()));

//        String test="ABC34cccddee";
//        System.out.println(test.toUpperCase());//小写转大写
//        System.out.println(test.toLowerCase());//大写转小写
//        String strjson = "{\n" +
//                "    \"act\": \"index\",\n" +
//                "    \"cart_list\": {\n" +
//                "        \"3074\": {\n" +
//                "            \"attr\": \"0\",\n" +
//                "            \"deal_id\": \"81\",\n" +
//                "            \"icon\": \"http://192.168.0.81/fangwei/public/attachment/201502/26/11/54ee8c68e932a_280x170.jpg\",\n" +
//                "            \"id\": \"3074\",\n" +
//                "            \"max\": 100,\n" +
//                "            \"name\": \"仅售39元！价值99元的魅货莫代尔不规则衫1件，魅货莫代尔不规则开衫\",\n" +
//                "            \"number\": \"1\",\n" +
//                "            \"return_score\": 0,\n" +
//                "            \"return_total_score\": 0,\n" +
//                "            \"sub_name\": \"魅货莫代尔不规则衫\",\n" +
//                "            \"total_price\": 39,\n" +
//                "            \"unit_price\": 39\n" +
//                "        },\n" +
//                "        \"3075\": {\n" +
//                "            \"attr\": \"0\",\n" +
//                "            \"deal_id\": \"80\",\n" +
//                "            \"icon\": \"http://192.168.0.81/fangwei/public/attachment/201502/26/10/54ee8c072cb42_280x170.jpg\",\n" +
//                "            \"id\": \"3075\",\n" +
//                "            \"max\": 100,\n" +
//                "            \"name\": \"仅售125元！价值698元的冰爱长袖针织披肩1件，冰爱长袖针织披肩10-披肩\",\n" +
//                "            \"number\": \"1\",\n" +
//                "            \"return_score\": 0,\n" +
//                "            \"return_total_score\": 0,\n" +
//                "            \"sub_name\": \"冰爱长袖针织披肩\",\n" +
//                "            \"total_price\": 126,\n" +
//                "            \"unit_price\": 126\n" +
//                "        }\n" +
//                "    },\n" +
//                "    \"city_id\": \"15\",\n" +
//                "    \"city_name\": \"福州\",\n" +
//                "    \"ctl\": \"cart\",\n" +
//                "    \"group_id\": 1,\n" +
//                "    \"has_mobile\": 1,\n" +
//                "    \"info\": \"\",\n" +
//                "    \"is_score\": 0,\n" +
//                "    \"page_title\": \"购物车\",\n" +
//                "    \"ref_uid\": \"\",\n" +
//                "    \"return\": 1,\n" +
//                "    \"sess_id\": \"5lpe0c7sp9db7tv6ps03fqt1v5\",\n" +
//                "    \"status\": 1,\n" +
//                "    \"total_data\": {\n" +
//                "        \"return_total_score\": 0,\n" +
//                "        \"total_price\": 165\n" +
//                "    },\n" +
//                "    \"user_login_status\": 1\n" +
//                "}";
//        Cart_checkActModel json = JSON.parseObject(strjson, Cart_checkActModel.class);
//        float a = 456;
//        float b = 58;
//        System.out.println(a/b);
//        System.out.println(a%b);
//        System.out.println( Math.round(1.9));
//        String[] aa = new String[]{"2172","-40","","0"};
//        System.out.print(getValueMaxLargeValue(getValueMaxMin(aa).get("max")));
    }
    public int getJiao(int x1, int y1, int x2, int y2){
        int x=Math.abs(x1-x2);
        int y=Math.abs(y1-y2);
        double z=Math.sqrt(x*x+y*y);
        return Math.round((float)(Math.asin(y/z)/Math.PI*180));//最终角度
    }
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
    /**
     * 获取数组里面的最大值和最小值
     * @param A
     * @return
     */
    public HashMap<String, Double> getValueMaxMin(String[] A){
        Double min,max;
        HashMap<String ,Double> map = new HashMap<>();
        min=max=Double.valueOf(A[0].equals("")?"0":A[0]);
        for(int i=0;i<A.length;i++)
        {
            if(Double.valueOf(A[i].equals("")?"0":A[i])>max)   // 判断最大值
                max=Double.valueOf(A[i].equals("")?"0":A[i]);
            if(Double.valueOf(A[i].equals("")?"0":A[i])<min)   // 判断最小值
                min=Double.valueOf(A[i].equals("")?"0":A[i]);
        }
        map.put("max",max);
        map.put("min",min);
        return map;
    }
}