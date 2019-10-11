package com.example.foss.controller;

import com.example.foss.pojo.JsonResult;
import com.example.foss.service.SupplierService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.foss.pojo.Supplier;

import java.util.List;

import static com.example.foss.Utils.TimeUtil.getNowTime;

@Controller
@RequiresRoles("admin")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @GetMapping("supplier")
    public String toSupplier(){
        return "manage_supplier";
    }
    @GetMapping("querySupplier")
    @ResponseBody
    public List<Supplier> querySupplier(@RequestParam(required = false,value = "sname") String sname){
        Supplier supplier = new Supplier();
        supplier.setSname(sname);
        return supplierService.querySupplier(supplier);
    }
    @PostMapping("insertSupplier")
    @ResponseBody
    public JsonResult insertSupplier(@RequestBody Supplier supplier){
        String time = getNowTime();
        supplier.setAddDate(time);
        supplier.setUpdateDate(time);
        JsonResult jsonResult = new JsonResult();
        if(supplierService.insertSupplier(supplier)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("插入成功 ");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("插入失败");
        }
        return jsonResult;
    }
    @PostMapping("updateSupplier/{sid}")
    @ResponseBody
    public JsonResult updateSupplier(@PathVariable("sid") Integer sid, @RequestBody Supplier supplier){
        String time = getNowTime();
        supplier.setUpdateDate(time);
        supplier.setSid(sid);
        JsonResult jsonResult = new JsonResult();
        if(supplierService.updateSupplier(supplier)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("修改成功 ");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("修改失败");
        }
        return jsonResult;
    }
    @PostMapping("deleteSupplier/{supplierId}")
    @ResponseBody
    public JsonResult deleteSupplier(@PathVariable("supplierId")Integer supplierId,@RequestBody Supplier supplier){
        String time = getNowTime();
        supplier.setUpdateDate(time);
        supplier.setSid(supplierId);
        JsonResult jsonResult = new JsonResult();
        if(supplierService.deleteSupplier(supplier)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("删除成功 ");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }
}
