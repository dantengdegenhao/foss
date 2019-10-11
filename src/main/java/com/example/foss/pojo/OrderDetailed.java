package com.example.foss.pojo;

import java.util.List;

public class OrderDetailed {
    private int id;
    private int orderId;
    private int foodId;
    private int foodNum;
    private float foodPrice;
    private Order order;
    private FoodInformation foodInformation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public FoodInformation getFoodInformation() {
        return foodInformation;
    }

    public void setFoodInformation(FoodInformation foodInformation) {
        this.foodInformation = foodInformation;
    }
}
