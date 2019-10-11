package com.example.foss.service.impl;

import com.example.foss.mapper.AdminDao;
import com.example.foss.pojo.FoodInformation;
import com.example.foss.pojo.FoodStatus;
import com.example.foss.pojo.FoodType;
import com.example.foss.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    public List<FoodType> queryFoodType(String typeName) {
        return adminDao.queryFoodType(typeName);
    }
    public int insertNewType(FoodType foodType) {
        return adminDao.insertNewType(foodType);
    }
    public int deleteFoodType(FoodType foodType){return  adminDao.deleteFoodType(foodType);}
    public int updateFoodType(FoodType foodType){return adminDao.updateFoodType(foodType);}
    public List<FoodInformation> queryFoodInformation(FoodInformation foodInformation) {
        return adminDao.queryFoodInformation(foodInformation);
    }
    public int insertNewFood(FoodInformation foodInformation) {
        return adminDao.insertNewFood(foodInformation);
    }
    public int deleteFood(FoodInformation foodInformation) {
        return adminDao.deleteFood(foodInformation);
    }
    public int updateFood(FoodInformation foodInformation) {
        return adminDao.updateFood(foodInformation);
    }

    public List<FoodStatus> queryFoodStatus(FoodStatus foodStatus) { return adminDao.queryFoodStatus(foodStatus);}
    public int insertFoodStatus(FoodStatus foodStatus) { return adminDao.insertFoodStatus(foodStatus); }
    public int upperFood(FoodStatus foodStatus) { return adminDao.upperFood(foodStatus); }
    public int lowerFood(FoodStatus foodStatus) { return adminDao.lowerFood(foodStatus); }
}
