package com.example.foss.service;

import com.example.foss.pojo.Shipping;

import java.util.List;

public interface ShippingService {
    public int add(Shipping shipping);
    public int delete(Long id);
    public int update(Shipping shipping);
    public List<Shipping> get(Long userId);
}
