package com.shop.model;

public class Supplier extends BaseModel {

    public Supplier(Integer id, String name, String description) {
        super(id, name, description);
    }

    public Supplier(String name, String description) {
        super(name, description);
    }
}