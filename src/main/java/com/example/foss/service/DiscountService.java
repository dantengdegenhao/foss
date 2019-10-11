package com.example.foss.service;

import com.example.foss.pojo.Discount;

import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/23
 * @description 折扣
 */
public interface DiscountService {
    public int add(Discount discount);
    public int delete(Long id);
    public int update(Discount discount);
    public List<Discount> get(Discount discount);
}
