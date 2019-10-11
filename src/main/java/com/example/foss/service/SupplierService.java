package com.example.foss.service;

import com.example.foss.pojo.Supplier;

import java.util.List;

public interface SupplierService {
    public List<Supplier> querySupplier(Supplier supplier);
    public int insertSupplier(Supplier supplier);
    public int updateSupplier(Supplier supplier);
    public int deleteSupplier(Supplier supplier);
}
