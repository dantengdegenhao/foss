package com.example.foss.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/23
 * @description 折扣
 */
@Data
public class Discount {
    private Long id;
    private Long foodId;
    private Float discount;
    @TableField(exist = false)
    private FoodInformation foodInformation;
}
