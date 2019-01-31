package com.gq.service;


import com.gq.model.User;

public interface UserService {
    User getUserById(Integer id);

    User findByName(String userName);
}
