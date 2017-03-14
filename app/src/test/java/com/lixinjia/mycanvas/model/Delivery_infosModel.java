package com.lixinjia.mycanvas.model;

/**
 * Created by maojianding on 2016/11/30.
 */

public class Delivery_infosModel {
    private String id;
    private String name;
    private int supplier_delivery_fee;

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

    public int getSupplier_delivery_fee() {
        return supplier_delivery_fee;
    }

    public void setSupplier_delivery_fee(int supplier_delivery_fee) {
        this.supplier_delivery_fee = supplier_delivery_fee;
    }

}
