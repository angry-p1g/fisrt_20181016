package com.gq.service.impl;


import com.gq.mapper.UserMapper;
import com.gq.model.User;
import com.gq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByName(String userName) {
        return userMapper.findByName(userName);
    }
}
