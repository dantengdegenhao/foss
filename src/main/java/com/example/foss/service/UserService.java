package com.example.foss.service;

import com.example.foss.pojo.User;

public interface UserService {
    public User getByMobile(String mobile);
    public int add(User user);
    public int revise(User user);
}
