package com.gq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gq.dao.UserDao;
import com.gq.model.User;
import com.gq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    /**
     * 将参数传给这个方法就可以实现物理分页
     * 只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> findAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users =  userDao.selectUsers();
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }
}
