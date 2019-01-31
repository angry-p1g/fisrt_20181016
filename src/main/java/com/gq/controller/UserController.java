//package com.gq.controller;
//
//import com.gq.model.User;
//import com.gq.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @ResponseBody
//    @RequestMapping(value = "/add" , produces = {"application/json;charset=UTF-8"})
//    public int addUser(User user){
//       return userService.addUser(user);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "findAll/{pageNum}/{pageSize}",produces = {"application/json;charset=UTF-8"})//courfer-sbm/findAll?pageNum=1&pageSize=5
//    public Object findAllUser(@PathVariable int pageNum,@PathVariable int pageSize){
//        return userService.findAllUsers(pageNum,pageSize);
//    }
//}
