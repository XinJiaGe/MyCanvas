package com.lixinjia.mycanvas.model;

/**
 * Created by maojianding on 2016/11/28.
 *       text	配送方式名称	string
 value	配送方式ID	number
 supplier_id	商户ID	string
 */

public class Supplier_consignee {
    private String text;
    private int value;
    private String supplier_id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    @Override
    public String toString()
    {
        return getText();
    }
}
