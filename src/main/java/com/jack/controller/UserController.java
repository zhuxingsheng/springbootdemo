package com.jack.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author JackChu (jackchu2015@foxmail.com)
 * @date 2017/1/10
 */
@RestController
public class UserController {

    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public String getUser(@PathVariable String username, @RequestParam String pwd){
        return "Welcome,"+username;
    }
}
