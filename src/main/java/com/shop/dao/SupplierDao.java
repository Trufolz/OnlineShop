package com.shop.dao;

import com.shop.model.Supplier;
import java.util.List;

interface SupplierDao {

    void add(Supplier supplier);

    Supplier find(Integer id);

    List<Supplier> getAll();
}
