package com.example.foss.service.impl;

import com.example.foss.mapper.OrderDao;
import com.example.foss.pojo.Order;
import com.example.foss.pojo.OrderDetailed;
import com.example.foss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public List<Order> queryOrder(Order order) {
        return orderDao.queryOrder(order);
    }

    @Override
    public List<OrderDetailed> queryDetailed(Integer orderId) {
        System.out.println(orderDao.queryDetailed(orderId));
        return orderDao.queryDetailed(orderId);
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @Override
    public int payOrder(Order order) {
        return orderDao.payOrder(order);
    }

    @Override
    public int deliverOrder(Order order) {
        return orderDao.deliverOrder(order);
    }

    @Override
    public int backOrder(Order order) {
        return orderDao.backOrder(order);
    }

    @Override
    public int endOrder(Order order) {
        return orderDao.endOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public int batchInsert(List<OrderDetailed> orderDetaileds) {
        return orderDao.batchInsert(orderDetaileds);
    }
}
