package com.shop.model;

abstract class BaseModel {

    Integer id;
    String name;
    String description;

    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    BaseModel(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
