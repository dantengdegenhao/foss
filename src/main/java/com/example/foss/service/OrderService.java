package com.example.foss.service;



import com.example.foss.pojo.Order;
import com.example.foss.pojo.OrderDetailed;

import java.util.List;

public interface OrderService {
    public List<Order> queryOrder(Order order);
    public List<OrderDetailed> queryDetailed(Integer orderId);
    public int createOrder(Order order);
    public int payOrder(Order order);
    public int deliverOrder(Order order);
    public int backOrder(Order order);
    public int endOrder(Order order);
    public int updateOrder(Order order);
    public int batchInsert(List<OrderDetailed> orderDetaileds);
}
