package com.shop.controller;

import com.shop.Application;
import com.shop.dao.ProductCategoryDaoImpl;
import com.shop.dao.ProductDaoImpl;
import com.shop.dao.SupplierDaoImpl;
import com.shop.model.Product;
import com.shop.model.ProductCategory;
import com.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ProductController {
    private SupplierController supplierController;

    public ProductController() {
        this.supplierController = new SupplierController();
    }

    public SupplierController getSupplierController() {
        return supplierController;
    }

    public ArrayList<Product> showAvailableProducts() {
        ArrayList<Product> products = new ProductDaoImpl(Application.getApp().getConnection()).getAll();
        return products;
    }

    public ArrayList<Product> showProductByName(Request req, Response res) {
        String productName = req.queryParams("search");
        System.out.println(productName);
        ProductDaoImpl productDao = new ProductDaoImpl(Application.getApp().getConnection());
        ArrayList<Product> products = productDao.getByName(productName);
        return products;
    }

    public Map<String, Object> renderProducts(List<Product> products) {
        Map<String, Object> params = new HashMap<>();
        params.put("products", products);
        params.put("categories", ProductCategoryController.showAvailableCategories());
        params.put("suppliers", supplierController.showAvailableSuppliers());
        return params;
    }

    public void addNewProduct(Request req, Response res) {
        String productName = req.queryParams("productName");
        String productDescription = req.queryParams("description");
        String productCategory = req.queryParams("category");
        String productSupplier = req.queryParams("supplier");
        String supplierDescription = req.queryParams("supplierDescription");
        String categoryDescription = req.queryParams("categoryDescription");
        String categoryDepartment = req.queryParams("categoryDepartment");
        Float productPrice = Float.parseFloat(req.queryParams("price"));
        if (productPrice < 0) {
            productPrice = productPrice * (-1);
        } else if (productPrice == 0.0) {
            productPrice += 10000000;
        }

        Supplier supplier = new Supplier(productSupplier, supplierDescription);
        new SupplierDaoImpl(Application.getApp().getConnection()).add(supplier);
        Integer supplierId = new SupplierDaoImpl(Application.getApp().getConnection()).findId(supplier);
        supplier.setId(supplierId);

        ProductCategory category = new ProductCategory(productCategory, categoryDepartment,
            categoryDescription);
        new ProductCategoryDaoImpl(Application.getApp().getConnection()).add(category);
        Integer categoryId = new ProductCategoryDaoImpl(Application.getApp().getConnection()).findId(category);
        category.setId(categoryId);

        Product product = new Product(productName, productPrice, productDescription, "PLN",
            category, supplier);
        new ProductDaoImpl(Application.getApp().getConnection()).add(product);
    }
}
