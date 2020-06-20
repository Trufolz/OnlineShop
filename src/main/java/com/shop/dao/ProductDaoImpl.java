package com.shop.dao;

import com.shop.Application;
import com.shop.model.Product;
import com.shop.model.ProductCategory;
import com.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {

    private Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Product product) {
        String sql =
            "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) "
                + "VALUES ('" + product.getName() + "','" + product.getPriceValue() + "','" + product
                .getDescription() + "','" + product.getDefaultCurrency() + "','" + product
                .getProductCategory().getId() + "','" + product.getSupplier().getId() + "');";
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * from Products where id = '" + id + "'";
        Product newProduct = null;
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                ProductCategory productCategory = new ProductCategoryDaoImpl(connection).find(idProductCategory);
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl(connection).find(idSupplier);
                newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
            }
        } catch (Exception e) {
        }
        return newProduct;
    }

    @Override
    public ArrayList<Product> getAll() {
        ArrayList<Product> listProducts = new ArrayList<>();
        String query = "SELECT * from Products";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                ProductCategory productCategory = new ProductCategoryDaoImpl(connection).find(idProductCategory);
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl(connection).find(idSupplier);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
        }
        return listProducts;
    }

    @Override
    public ArrayList<Product> getBy(Supplier supplier) {
        ArrayList<Product> listProducts = new ArrayList<>();
        Integer supplierId = supplier.getId();
        String query = "SELECT * FROM Products WHERE id_supplier = '" + supplierId + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                ProductCategory productCategory = new ProductCategoryDaoImpl(connection).find(idProductCategory);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
        }
        return listProducts;
    }

    public ArrayList<Product> getBy(ProductCategory productCategory) {
        ArrayList<Product> listProducts = new ArrayList<>();
        Integer productCategoryId = productCategory.getId();
        String query = "SELECT * from Products WHERE id_productCategory = '" + productCategoryId + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl(connection).find(idSupplier);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
        }
        return listProducts;
    }

    public ArrayList<Product> getByName(String searchName) {
        ArrayList<Product> listProducts = new ArrayList<>();
        String query = "SELECT * from Products WHERE name LIKE '%" + searchName + "%'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl(connection).find(idSupplier);
                ProductCategory productCategory = new ProductCategoryDaoImpl(connection).find(idProductCategory);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
        }
        return listProducts;
    }
}
