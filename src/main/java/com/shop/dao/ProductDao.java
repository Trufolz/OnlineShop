package com.shop.dao;

import com.shop.model.Product;
import com.shop.model.ProductCategory;
import com.shop.model.Supplier;
import java.util.List;

interface ProductDao {

    void add(Product product);

    Product find(int id);

    List<Product> getAll();

    List<Product> getBy(Supplier supplier);

    List<Product> getBy(ProductCategory productCategory);

  List<Product> getByName(String name);

}
