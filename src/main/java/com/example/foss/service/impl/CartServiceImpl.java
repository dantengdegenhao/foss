package com.example.foss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.foss.mapper.CartMapper;
import com.example.foss.pojo.Cart;
import com.example.foss.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/16
 * @description 购物车
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public int add(Cart cart) {
        return cartMapper.insert(cart);
    }

    @Override
    public int delete(Long id) {
        return cartMapper.deleteById(id);
    }

    @Override
    public int update(Cart cart) {
        return cartMapper.updateById(cart);
    }

    @Override
    public List<Cart> get(Long userId) {
        /*QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",userId);*/
        return cartMapper.getByUserId(userId);
    }
}
