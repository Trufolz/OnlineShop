package com.shop.dao;

import com.shop.Application;
import com.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

    private Connection connection;

    public ProductCategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(ProductCategory category) {
        String sql =
            "INSERT INTO ProductCategories (name, department, description) "
                + "VALUES ('" + category.getName() + "','" + category
                .getDepartment() + "','" + category.getDescription() + "');";
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
        }
    }

    @Override
    public ProductCategory find(Integer idToFind) {
        String query =
            "SELECT id, name, department, description FROM ProductCategories WHERE id = '"
                + idToFind + "'";
        ProductCategory newProductCategory = null;

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                newProductCategory = new ProductCategory(id, name, department, description);
            }
        } catch (Exception e) {
        }
        return newProductCategory;
    }

    @Override
    public ArrayList<ProductCategory> getAll() {
        ArrayList<ProductCategory> listCategories = new ArrayList<>();
        String query = "SELECT * from ProductCategories ORDER BY name";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                ProductCategory productCategory = new ProductCategory(id, name, department,
                    description);
                listCategories.add(productCategory);
            }
        } catch (Exception e) {
        }
        return listCategories;
    }

    public Integer findId(ProductCategory category) {
        String query =
            "SELECT id FROM ProductCategories WHERE name = '"
                + category.getName() + "' AND description = '" + category.getDescription() + "'";
        Integer id = null;
        try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
        }
        return id;
    }
}
