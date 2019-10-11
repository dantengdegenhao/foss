package com.example.foss.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.foss.pojo.FoodInformation;
import com.example.foss.pojo.JsonResult;
import com.example.foss.pojo.Stock;
import com.example.foss.mapper.StockSqlProvider;
import com.example.foss.service.AdminService;
import com.example.foss.service.StockService;

import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/18
 * @description 库存
 */
@RestController
@RequiresRoles("admin")
@RequestMapping("/stock")
@Slf4j
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private AdminService adminService;
    @PostMapping("")
    public JsonResult add(@RequestBody Stock stock){
        JsonResult jsonResult=new JsonResult();
        try {
            if (stockService.add(stock)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("添加成功");
            }else {
                jsonResult.setCode(-1);
                jsonResult.setMsg("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(-2);
            jsonResult.setMsg("添加异常");
        }
        return jsonResult;
    }
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Long id){
        JsonResult jsonResult=new JsonResult();
        try {
            if (stockService.delete(id)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("删除成功");
            }else {
                jsonResult.setCode(-1);
                jsonResult.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(-2);
            jsonResult.setMsg("删除异常");
        }
        return jsonResult;
    }
    @PutMapping("")
    public JsonResult update(@RequestBody Stock stock){
        JsonResult jsonResult=new JsonResult();
        try {
            if (stockService.update(stock)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("跟新成功");
            }else {
                jsonResult.setCode(-1);
                jsonResult.setMsg("跟新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(-2);
            jsonResult.setMsg("跟新异常");
        }
        return jsonResult;
    }
    @GetMapping("")
    public List<Stock> get(@RequestParam(value = "foodName",required = false)String foodName,@RequestParam(value = "isLowerMinPurchase",required = false)
                           Boolean isLowerMinPurchase,@RequestParam(value = "isExpiryDate",required = false) Boolean isExpiryDate)throws Exception{
            Stock stock = new Stock();
            stock.setIsExpiryDate(isExpiryDate);
            stock.setIsLowerMinPurchase(isLowerMinPurchase);
            stock.setFoodInformation(new FoodInformation());
            if (StringUtils.isNotBlank(foodName)) {
                stock.getFoodInformation().setFoodName(foodName);
            }
            return stockService.get(stock);
    }
    @GetMapping("/test")
    public String test(){
        Stock stock=new Stock();
        stock.getFoodInformation().setFoodName("奥利");
        StockSqlProvider stockSqlProvider=new StockSqlProvider();
        log.info(stockSqlProvider.select(stock));
        return stockSqlProvider.select(stock);
    }

}
