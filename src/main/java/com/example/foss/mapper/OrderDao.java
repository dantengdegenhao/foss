package com.example.foss.mapper;

import com.example.foss.pojo.Order;
import com.example.foss.pojo.OrderDetailed;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface OrderDao {
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
