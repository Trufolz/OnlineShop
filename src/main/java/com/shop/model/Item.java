package com.shop.model;

public class Item {

    private static Integer nextId = 1;
    private final Integer id;
    private final Integer idBasket;
    private final Product product;
    private Integer quantity;
    private Float totalPrice;

    public Item(Product product, Integer quantity, Integer idBasket) {
        this.id = Item.nextId++;
        this.product = product;
        this.quantity = quantity;
        this.idBasket = idBasket;
        this.totalPrice = getTotalPrice();
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getTotalPrice() {
        return this.product.getDefaultPrice() * this.quantity;
    }

    public Integer getId() {
        return this.id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
