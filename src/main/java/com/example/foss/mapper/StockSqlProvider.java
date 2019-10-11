package com.example.foss.mapper;

import com.example.foss.pojo.Stock;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.jdbc.SQL;


/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/19
 * @description 动态语句类
 */
public class StockSqlProvider {
    //查询
    public String select(Stock stock) {
        return new SQL() {{
            SELECT("*");
            FROM("stock");
            LEFT_OUTER_JOIN("foodInformation on foodInformation.foodId=stock.food_id");
            WHERE("foodInformation.deleteTag = 0");
            if (stock != null) {
               /* if (student.getId() != null) {
                    WHERE("id = #{id}");
                }
                if (student.getUsername() != null) {
                    WHERE("username like '%' || #{username} || '%'");
                }
                if (student.getPassword() != null) {
                    WHERE("password = #{password}");
                }
                if (student.getAddr() != null) {
                    WHERE("addr like '%' || #{addr} || '%' ");
                }*/
               if (BooleanUtils.isTrue(stock.getIsLowerMinPurchase())){
                   WHERE("number <= minPurchase");
               }
               if (BooleanUtils.isFalse(stock.getIsLowerMinPurchase())){
                   WHERE("number > minPurchase");
               }
               if (BooleanUtils.isTrue(stock.getIsExpiryDate())){
                   WHERE("expiryDate <= DATE_ADD(CURDATE(),INTERVAL 2 MONTH)");
               }
               if (stock.getFoodInformation()!=null){
                   if (stock.getFoodInformation().getFoodName()!=null){
                       WHERE("foodInformation.foodName like concat ('%',#{foodInformation.foodName},'%')");
                   }
               }
            }
        }}.toString();
    }
}
