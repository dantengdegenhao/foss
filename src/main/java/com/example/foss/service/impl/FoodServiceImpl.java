package com.example.foss.service.impl;

import com.example.foss.mapper.FoodDao;
import com.example.foss.pojo.FoodInformation;
import com.example.foss.pojo.FoodStatus;
import com.example.foss.pojo.FoodType;
import com.example.foss.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodDao foodDao;
    public List<FoodType> queryFoodType(String typeName) {
        return foodDao.queryFoodType(typeName);
    }
    public int insertNewType(FoodType foodType) {
        return foodDao.insertNewType(foodType);
    }
    public int deleteFoodType(FoodType foodType){return  foodDao.deleteFoodType(foodType);}
    public int updateFoodType(FoodType foodType){return foodDao.updateFoodType(foodType);}
    public List<FoodInformation> queryFoodInformation(FoodInformation foodInformation) {
        return foodDao.queryFoodInformation(foodInformation);
    }
    public int insertNewFood(FoodInformation foodInformation) {
        return foodDao.insertNewFood(foodInformation);
    }
    public int deleteFood(FoodInformation foodInformation) {
        return foodDao.deleteFood(foodInformation);
    }
    public int updateFood(FoodInformation foodInformation) {
        return foodDao.updateFood(foodInformation);
    }

    public List<FoodStatus> queryFoodStatus(FoodStatus foodStatus) { return foodDao.queryFoodStatus(foodStatus);}
    public int insertFoodStatus(FoodStatus foodStatus) { return foodDao.insertFoodStatus(foodStatus); }
    public int upperFood(FoodStatus foodStatus) { return foodDao.upperFood(foodStatus); }
    public int lowerFood(FoodStatus foodStatus) { return foodDao.lowerFood(foodStatus); }
}
