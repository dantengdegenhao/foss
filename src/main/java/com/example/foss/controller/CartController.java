package com.example.foss.controller;

import com.example.foss.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/23
 * @description 购物车
 */
@RestController
public class CartController {
    @Autowired
    private CartService cartService;


}
