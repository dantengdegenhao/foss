package com.example.foss.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/18
 * @description 库存
 */
@Data
public class Stock {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer number;
    private Long userId;
    private Long foodId;
    private Float quantity;
    @TableField(value = "minPurchase")
    private Integer minPurchase;
    @TableField(value = "minStock")
    private Integer minStock;
    @TableField(value = "expiryDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date expiryDate;
    @TableField(exist = false)
    private FoodInformation foodInformation;
    @TableField(exist = false)
    private Boolean isLowerMinPurchase;
    @TableField(exist = false)
    private Boolean isExpiryDate;
}
