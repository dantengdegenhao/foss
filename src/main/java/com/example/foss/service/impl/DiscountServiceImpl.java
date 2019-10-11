package com.example.foss.service.impl;

import com.example.foss.mapper.DiscountDao;
import com.example.foss.pojo.Discount;
import com.example.foss.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/23
 * @description 折扣
 */
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountDao discountDao;
    @Override
    public int add(Discount discount) {
        return discountDao.insert(discount);
    }

    @Override
    public int delete(Long id) {
        return discountDao.deleteById(id);
    }

    @Override
    public int update(Discount discount) {
        return discountDao.updateById(discount);
    }

    @Override
    public List<Discount> get(Discount discount) {
        return discountDao.get(discount);
    }
}
