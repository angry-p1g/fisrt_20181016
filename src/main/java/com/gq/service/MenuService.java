package com.gq.service;


import com.gq.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenusByUserId(Integer userId);
}
