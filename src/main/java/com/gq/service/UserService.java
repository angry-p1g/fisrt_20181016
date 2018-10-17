package com.gq.service;

import com.github.pagehelper.PageInfo;
import com.gq.model.User;


public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 查询所有的用户
     * @return
     */
    PageInfo<User> findAllUsers(int pageNum, int pageSize);

}
