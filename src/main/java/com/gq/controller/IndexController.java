package com.gq.controller;

import com.gq.model.Notice;
import com.gq.model.User;
import com.gq.service.NoticeService;
import com.gq.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/index")
    public User index() {
        return userService.getUserById(1);
    }

    @RequiresPermissions("test")
    @RequestMapping("/test")
    public String test() {
        return "ok";
    }

    @RequestMapping("/cache")
    public Notice cache() {
        Notice notice=noticeService.selectByPrimaryKey(6);
        return notice;
    }
}
