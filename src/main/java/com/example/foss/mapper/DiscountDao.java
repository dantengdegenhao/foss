package com.example.foss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.foss.pojo.Discount;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/23
 * @description 折扣
 */
public interface DiscountDao extends BaseMapper<Discount> {
    @SelectProvider(type = StockSqlProvider.class,method = "select")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "food_id",property = "foodId"),
            @Result(column = "discount",property = "discount"),
            @Result(column = "foodName",property = "foodInformation.foodName")
    })
    List<Discount> get(Discount discount);
}
