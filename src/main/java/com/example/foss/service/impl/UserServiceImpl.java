package com.example.foss.service.impl;

import com.example.foss.mapper.UserMapper;
import com.example.foss.pojo.User;
import com.example.foss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.foss.Utils.Md5Utils.MD5Pwd;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/10
 * @description 用户
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getByMobile(String mobile) {
        /*QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("mobile",mobile);*/
        return userMapper.selectById(mobile);
    }

    @Override
    public int add(User user) {
        Map map = MD5Pwd(user.getMobile(), user.getPassword());
        user.setPassword((String) map.get("password"));
        user.setSalt((String) map.get("salt"));
        return userMapper.insert(user);
    }

    @Override
    public int revise(User user) {
        /*UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.ge("mobile",user.getMobile());
        userMapper.update(user,userUpdateWrapper);*/
        Map map = MD5Pwd(user.getMobile(), user.getPassword());
        user.setPassword((String) map.get("password"));
        user.setSalt((String) map.get("salt"));
        return userMapper.updateById(user);
    }
}
