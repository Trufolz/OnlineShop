package com.shop.controller;

import com.shop.Application;
import com.shop.dao.ProductDaoImpl;
import com.shop.dao.SupplierDaoImpl;
import com.shop.model.Product;
import com.shop.model.Supplier;
import java.util.ArrayList;
import spark.Request;
import spark.Response;

public class SupplierController {

    public ArrayList<Supplier> showAvailableSuppliers() {
        ArrayList<Supplier> suppliers = new SupplierDaoImpl(Application.getApp().getConnection()).getAll();
        return suppliers;
    }

    public ArrayList<Product> productBySuppliers(Request req, Response res) {
        Integer supplierId = Integer.parseInt(req.params(":id"));
        Supplier supplier;
        ArrayList<Product> productsFromSupplier;
        while (true) {
            try {
                supplier = new SupplierDaoImpl(Application.getApp().getConnection()).find(supplierId);
                productsFromSupplier = new ProductDaoImpl(Application.getApp().getConnection()).getBy(supplier);
                if (supplier != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return productsFromSupplier;
    }
}
