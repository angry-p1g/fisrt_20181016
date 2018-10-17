package com.gq.dao;

import com.gq.model.User;

import java.util.List;

public interface UserDao {
    int insert(User record);


    List<User> selectUsers();
}
