package com.lixinjia.mycanvas.model;

/**
 * Created by maojianding on 2016/11/28.
 *  supplier_preferential	商户优惠列表	array<object>
 text	优惠名称	string
 value	优惠ID	number
 */

public class Supplier_preferential {
    private String text;
    private int value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
