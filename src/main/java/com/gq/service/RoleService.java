package com.gq.service;

import com.gq.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRolesByUserId(Integer userId);
}
