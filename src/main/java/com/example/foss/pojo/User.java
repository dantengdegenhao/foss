package com.example.foss.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/10
 * @description 用户实体类
 */
@Data
@TableName(value = "user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotBlank(message = "名字不能为空")
    private String name;
    @TableId
    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "无效的电话号码")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String salt;
    @TableField(exist = false)
    @NotBlank(message = "密码不能为空")
    private String determinePassword;
    private Long roleId;
    @TableLogic
    private Integer deleted;
    private Integer integral;
    @TableField(exist = false)
    @NotBlank(message = "验证码不能为空")
    private String code;
    @TableField(exist = false)
    private Role role;

    public User(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "无效的电话号码") String mobile, @NotBlank(message = "密码不能为空") String password, @NotBlank(message = "密码不能为空") String determinePassword) {
        this.mobile = mobile;
        this.password = password;
        this.determinePassword = determinePassword;
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
