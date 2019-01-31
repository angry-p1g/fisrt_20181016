package com.gq.service.impl;


import com.gq.mapper.MenuMapper;
import com.gq.model.Menu;
import com.gq.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findMenusByUserId(Integer userId) {
        return menuMapper.findMenusByUserId(userId);
    }
}
