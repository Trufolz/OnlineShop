package com.shop.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Basket {

    private final ArrayList<Item> itemsList;
    private static Integer nextId = 1;
    private final Integer id;

    public Basket(ArrayList<Item> items) {
        this.itemsList = items;
        this.id = Basket.nextId++;
    }

    public Integer getId() {
        return this.id;
    }

    public Float getTotalPrice() {
        return setTotalPrice();
    }

    private Float setTotalPrice() {
        Float orderPrice = 0.0f;
        for (Item item : this.itemsList) {
            orderPrice = orderPrice + item.getTotalPrice();
        }

        return orderPrice;
    }

    public ArrayList<Item> getItemList() {
        return this.itemsList;
    }

    public void addProduct(Item item) {
        itemsList.add(item);
    }

    public Iterator<Item> getIterator() {
        return this.itemsList.iterator();
    }

}
