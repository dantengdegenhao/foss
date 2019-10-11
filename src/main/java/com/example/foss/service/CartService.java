package com.example.foss.service;


import com.example.foss.pojo.Cart;

import java.util.List;

public interface CartService {
    public int add(Cart cart);
    public int delete(Long id);
    public int update(Cart cart);
    public List<Cart> get(Long userId);
}
