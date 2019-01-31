package com.gq.service.impl;


import com.gq.mapper.RoleMapper;
import com.gq.model.Role;
import com.gq.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }
}
