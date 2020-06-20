package com.shop.dao;

import com.shop.model.ProductCategory;
import java.util.List;

interface ProductCategoryDao {

    void add(ProductCategory category);

    ProductCategory find(Integer id);

    List<ProductCategory> getAll();

}
