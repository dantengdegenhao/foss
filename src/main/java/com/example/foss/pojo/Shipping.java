package com.example.foss.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/16
 * @description 地址表
 */
@Data
public class Shipping {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private String mobile;
    private String province;
    private String address;
    private String zip;
}
