package com.example.foss.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/10
 * @description 角色实体类
 */
@Data
@TableName("role")
public class Role {
    @TableId
    private Long id;
    private String role;
    private String remark;
}
