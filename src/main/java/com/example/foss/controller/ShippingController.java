package com.example.foss.controller;

import com.example.foss.pojo.Shipping;
import com.example.foss.service.CartService;
import com.example.foss.service.ShippingService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/23
 * @description 地址
 */
@Controller
@RequiresRoles(value = {"vip0","vip1","vip2","vip3"},logical = Logical.OR)
public class ShippingController {
    @Autowired
    private ShippingService shippingService;
    @Autowired
    private CartService cartService;
     @PostMapping("/shipping")
    public String add (Shipping shipping, Model model){
         int add = shippingService.add(shipping);
         model.addAttribute("addresslist",shippingService.get(shipping.getUserId()));
         model.addAttribute("cartlist",cartService.get(shipping.getUserId()));
         return "redirect:/shippingshow?id="+shipping.getUserId();
    }

    @GetMapping("/shipping")
    public List<Shipping> get(Long id){

         return shippingService.get(id);
    }
    @GetMapping("/shippingshow")
    public String address(Long id,Model model){
         model.addAttribute("addresslist",shippingService.get(id));
        model.addAttribute("cartlist",cartService.get(id));
         return "address/address";
    }
    @GetMapping("/goods3")
    public String goods(){
        return "qiantai/goods3";
    }
}
