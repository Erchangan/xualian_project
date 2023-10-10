package com.example.xunlian_interface.controller;


import com.example.xunlian_client_sdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class UserController {
    
    @GetMapping("/getName")
    public String getName(String name){
        return name;
    }

    @PostMapping("/postName")
    public User postName(@RequestBody User user, HttpServletRequest httpServletRequest){
        return user;
    }


}
