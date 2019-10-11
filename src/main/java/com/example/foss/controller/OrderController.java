package com.example.foss.controller;

import com.example.foss.pojo.JsonResult;
import com.example.foss.pojo.Order;
import com.example.foss.pojo.OrderDetailed;
import com.example.foss.pojo.OrderWithDetailed;
import com.example.foss.service.OrderService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.foss.Utils.TimeUtil.getNowTime;


@Controller
/*@RequiresRoles(value = {"vip0","vip1","vip2","vip3","admin"},logical = Logical.OR)*/
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("inCompleteOrder")
    public String toIncompleteOrder(){
        return "manage_incompleteOrder";
    }
    @GetMapping("completeOrder")
    public String toCompleteOrder(){
        return "manage_completeOrder";
    }
    @GetMapping("queryOrder/{statusdId}")
    @ResponseBody
    public List<Order> queryOrder(@PathVariable("statusdId")Integer statusId, @RequestParam(required = false,value="orderNum")String orderNum,
                                  @RequestParam(required = false,value="userId")Integer userId, @RequestParam(required = false,value="orderStatus")String orderStatus){
        Order order = new Order();
        order.setStatusId(statusId);
        if(orderNum!=null){
            order.setOrderNum(orderNum);
        };
        if(userId!=null){
            order.setUserId(userId);
        }
        if(orderStatus!=null){
            order.setOrderStatus(orderStatus);
        }
        return orderService.queryOrder(order);
    };
    @GetMapping("queryDetailed")
    @ResponseBody
    public List<OrderDetailed> queryDetailed(@RequestParam("orderId") Integer orderId){
        return  orderService.queryDetailed(orderId);
    }
    @GetMapping("createOrder")
    public String toCreateOrder(){
        return "createOrderTest";
    }
    @PostMapping("createOrder")
    @ResponseBody
    public JsonResult createOrder(@RequestBody OrderWithDetailed orderWithDetailed){
        String orderNum = (new Date()).toString();
        Order order = orderWithDetailed.getOrder();
        List<OrderDetailed> orderDetaileds = orderWithDetailed.getOrderDetaileds();
        order.setOrderNum(orderNum);
        order.setCreateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        int orderId  = 0;
        if(orderService.createOrder(order)>0){
            orderId = order.getOrderId();
            for(int i=0;i<orderDetaileds.size();i++){
                orderDetaileds.get(i).setOrderId(orderId);
            }
            if(orderService.batchInsert(orderDetaileds)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("成功");
            }else{
                jsonResult.setCode(-1);
                jsonResult.setMsg("失败");
            }
        }
        else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("失败");
        }
        return jsonResult;
    }
    @PostMapping("payOrder/{orderId}")
    @ResponseBody
    public JsonResult payOrder(@PathVariable("orderId") Integer orderId,@RequestBody Order order){
        JsonResult jsonResult = new JsonResult();
        order.setOrderId(orderId);
        if(orderService.payOrder(order)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("付款成功");
        }else{
            jsonResult.setCode(0);
            jsonResult.setMsg("付款失败");
        }
        return jsonResult;
    }
    @PostMapping("deliverOrder/{orderId}")
    @ResponseBody
    public JsonResult deliverOrder(@PathVariable("orderId") Integer orderId,@RequestBody Order order){
        JsonResult jsonResult = new JsonResult();
        order.setOrderId(orderId);
        order.setEndDate(getNowTime());
        if(orderService.deliverOrder(order)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("发货成功");
        }else{
            jsonResult.setCode(0);
            jsonResult.setMsg("发货失败");
        }
        return jsonResult;
    }
    @PostMapping("endOrder/{orderId}")
    @ResponseBody
    public JsonResult backOrder(@PathVariable("orderId") Integer orderId,@RequestBody Order order){
        JsonResult jsonResult = new JsonResult();
        order.setOrderId(orderId);
        order.setEndDate(getNowTime());
        if(orderService.endOrder(order)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("订单完成");
        }else{
            jsonResult.setCode(0);
            jsonResult.setMsg("订单异常");
        }
        return jsonResult;
    }
    @PostMapping("updateOrder/{orderId}")
    @ResponseBody
    public JsonResult updateOrder(@PathVariable("orderId") Integer orderId,@RequestBody Order order){
        JsonResult jsonResult = new JsonResult();
        order.setOrderId(orderId);
        if(orderService.updateOrder(order)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("修改成功");
        }else{
            jsonResult.setCode(0);
            jsonResult.setMsg("修改失败");
        }
        return jsonResult;
    }

}
