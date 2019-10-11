package com.example.foss.mapper;

import com.example.foss.pojo.FoodInformation;
import com.example.foss.pojo.FoodStatus;
import com.example.foss.pojo.FoodType;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface AdminDao {
    public List<FoodType> queryFoodType(String typeName);
    public int insertNewType(FoodType foodType);
    public int deleteFoodType(FoodType foodType);
    public int updateFoodType(FoodType foodType);

    public List<FoodInformation> queryFoodInformation(FoodInformation foodInformation);
    public List<FoodInformation> queryFoodInformationById(Long foodId);
    public int insertNewFood(FoodInformation foodInformation);
    public int deleteFood(FoodInformation foodInformation);
    public int updateFood(FoodInformation foodInformation);

    public List<FoodStatus> queryFoodStatus(FoodStatus foodStatus);
    public int insertFoodStatus(FoodStatus foodStatus);
    public int upperFood(FoodStatus foodStatus);
    public int lowerFood(FoodStatus foodStatus);
}
