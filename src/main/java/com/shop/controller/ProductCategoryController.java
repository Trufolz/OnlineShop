package com.shop.controller;

import com.shop.Application;
import com.shop.dao.ProductCategoryDaoImpl;
import com.shop.dao.ProductDaoImpl;
import com.shop.model.Product;
import com.shop.model.ProductCategory;
import java.util.ArrayList;
import spark.Request;
import spark.Response;


public class ProductCategoryController {

    static ArrayList<ProductCategory> showAvailableCategories() {
        return new ProductCategoryDaoImpl(Application.getApp().getConnection()).getAll();
    }

    public ArrayList<Product> showProductsFromCategory(Request req, Response res) {
        Integer categoryId = Integer.parseInt(req.params(":id"));
        ProductCategory productCategory;
        ArrayList<Product> productsFromCategory;
        while (true) {
            try {
                productCategory = new ProductCategoryDaoImpl(Application.getApp().getConnection()).find(categoryId);
                productsFromCategory = new ProductDaoImpl(Application.getApp().getConnection()).getBy(productCategory);
                if (productCategory != null) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return productsFromCategory;
    }


}
