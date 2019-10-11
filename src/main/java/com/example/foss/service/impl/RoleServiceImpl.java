package com.example.foss.service.impl;

import com.example.foss.mapper.RoleMapper;
import com.example.foss.pojo.Role;
import com.example.foss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/10
 * @description 角色
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    public Role getById(Long id){
        return roleMapper.selectById(id);
    }
}
