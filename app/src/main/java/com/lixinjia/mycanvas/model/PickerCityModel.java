package com.lixinjia.mycanvas.model;

/**
 * Created by 李忻佳 on 2016/9/21.
 */

public class PickerCityModel {
    private String id;
    private String name;
    public PickerCityModel() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PickerCityModel [name=" + name + "]";
    }
    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return name;
    }
}
