package com.example.foss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.foss.mapper.ShippingMapper;
import com.example.foss.pojo.Shipping;
import com.example.foss.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/16
 * @description 收货地址
 */
@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    @Override
    public int add(Shipping shipping) {
        return shippingMapper.insert(shipping);
    }

    @Override
    public int delete(Long id) {
        return shippingMapper.deleteById(id);
    }

    @Override
    public int update(Shipping shipping) {
        return shippingMapper.updateById(shipping);
    }

    @Override
    public List<Shipping> get(Long userId) {
        QueryWrapper<Shipping> shippingQueryWrapper = new QueryWrapper<>();
        shippingQueryWrapper.eq("user_id",userId);
        return shippingMapper.selectList(shippingQueryWrapper);
    }
}
