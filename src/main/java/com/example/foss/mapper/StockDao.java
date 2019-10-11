package com.example.foss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.foss.pojo.Stock;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/18
 * @description 库存
 */
@Repository
public interface StockDao extends BaseMapper<Stock> {

    @SelectProvider(type = StockSqlProvider.class,method = "select")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "number",property = "number"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "minPurchase",property = "minPurchase"),
            @Result(column = "minStock",property = "minStock"),
            @Result(column = "expiryDate",property = "expiryDate"),
            @Result(column = "foodName",property = "foodInformation.foodName"),
            @Result(column = "foodPrice",property = "foodInformation.foodPrice"),
            @Result(column = "operator",property = "foodInformation.operator")
    })
    List<Stock> get(Stock stock);
}
