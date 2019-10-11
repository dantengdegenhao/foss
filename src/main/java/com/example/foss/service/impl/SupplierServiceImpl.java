package com.example.foss.service.impl;

import com.example.foss.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.foss.mapper.SupplierDao;
import com.example.foss.pojo.Supplier;
import java.util.List;
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierDao supplierDao;
    @Override
    public List<Supplier> querySupplier(Supplier supplier) {
        return supplierDao.querySupplier(supplier);
    }

    @Override
    public int insertSupplier(Supplier supplier) {
        return supplierDao.insertSupplier(supplier);
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierDao.updateSupplier(supplier);
    }

    @Override
    public int deleteSupplier(Supplier supplier) {
        return supplierDao.deleteSupplier(supplier);
    }
}
