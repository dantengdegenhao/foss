package com.example.foss.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.foss.pojo.Supplier;

import java.util.List;
@Mapper
public interface SupplierDao {
    public List<Supplier> querySupplier(Supplier supplier);
    public int insertSupplier(Supplier supplier);
    public int updateSupplier(Supplier supplier);
    public int deleteSupplier(Supplier supplier);
}
