package com.shop.model;

import java.util.ArrayList;

public class ProductCategory extends BaseModel {
    private String department;
    private ArrayList<Product> products;

    public ProductCategory(Integer id, String name, String department, String description) {
        super(id, name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}