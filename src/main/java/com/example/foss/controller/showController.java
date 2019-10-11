package com.example.foss.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/19
 * @description 视图
 */
@Controller
@RequiresRoles("admin")
public class showController {
    @GetMapping("/showstock")
    public String tostock(){
        return "manage_Stock";
    }
    @GetMapping("/showMinPurchase")
    public String toMinPurchase(){
        return "manage_MinPurchase";
    }
    @GetMapping("/showMinStock")
    public String toMinStock(){
        return "manage_MinStock";
    }
    @GetMapping("/showInventory")
    public String toInventory(){
        return "manage_Inventory";
    }
    @GetMapping("/showExpiryDate")
    public String toExpiryDate(){
        return "manage_ExpiryDate";
    }
}
