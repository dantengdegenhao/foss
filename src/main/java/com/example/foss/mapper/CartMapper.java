package com.example.foss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.foss.pojo.Cart;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select * from cart where user_id = #{userId}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "food_id",property = "foodId"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "number",property = "number"),
            @Result(column = "food_id",property = "foodInformation",one = @One(select = "com.example.foss.mapper.FoodInformationMapper.selectById",fetchType = FetchType.DEFAULT))
    })
    List<Cart> getByUserId(Long userId);
}
