package com.example.foss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.foss.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
