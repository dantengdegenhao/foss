package com.example.foss.pojo;

import java.util.List;

public class OrderWithDetailed {
    private Order order;
    private List<OrderDetailed> orderDetaileds;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetailed> getOrderDetaileds() {
        return orderDetaileds;
    }

    public void setOrderDetaileds(List<OrderDetailed> orderDetaileds) {
        this.orderDetaileds = orderDetaileds;
    }

    @Override
    public String toString() {
        return "OrderWithDetailed{" +
                "order=" + order +
                ", orderDetaileds=" + orderDetaileds +
                '}';
    }
}
