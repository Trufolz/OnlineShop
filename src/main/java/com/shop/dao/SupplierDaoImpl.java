package com.shop.dao;

import com.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SupplierDaoImpl implements SupplierDao {

    private Connection connection;

    public SupplierDaoImpl (Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Supplier supplier) {
        String sql =
            "INSERT INTO Suppliers (name, description) "
                + "VALUES ('" + supplier.getName() + "','" + supplier
                .getDescription() + "');";
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {}
    }

    @Override
    public Supplier find(Integer idToFind) {
        String query = "SELECT id, name, description FROM Suppliers WHERE id = '" + idToFind + "'";
        Supplier newSupplier = null;
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                newSupplier = new Supplier(id, name, description);
            }
        } catch (Exception e) {}
        return newSupplier;
    }

    @Override
    public ArrayList<Supplier> getAll() {
        ArrayList<Supplier> listSuppliers = new ArrayList<>();
        String query = "SELECT * from Suppliers ORDER BY name";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Supplier supplier = new Supplier(id, name, description);
                listSuppliers.add(supplier);
            }
        } catch (Exception e) {}
        return listSuppliers;
    }

    public Integer findId(Supplier supplier) {
        String query =
            "SELECT id FROM Suppliers WHERE name = '" + supplier.getName() + "' AND description = '"
                + supplier.getDescription() + "'";
        Integer id = null;
        try (ResultSet resultSet = connection.createStatement().executeQuery(query);) {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {}
        return id;
    }
}
