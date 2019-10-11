package com.example.foss.mapper;

import com.example.foss.pojo.Discount;
import com.example.foss.pojo.Stock;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/24
 * @description
 */
public class DiscountSqlProvider {
    //查询
    public String select(Discount discount) {
        return new SQL() {{
            SELECT("*");
            FROM("discount");
            WHERE("foodInformation.foodId=discount.food_id");
            WHERE("foodInformation.deleteTag = 0");
            if (discount != null) {
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
                if (discount.getFoodInformation()!=null){
                    if (discount.getFoodInformation().getFoodName()!=null){
                        WHERE("foodInformation.foodName like concat ('%',#{foodInformation.foodName},'%')");
                    }
                }
            }
        }}.toString();
    }
}
