package com.example.xunlian_interface.controller;


import com.example.xunlian_client_sdk.model.User;

import com.example.xunlian_client_sdk.utils.SignUtils;
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
        //String accessKey = httpServletRequest.getHeader("accessKey");
        //String nonce = httpServletRequest.getHeader("nonce");
        //String timestamp = httpServletRequest.getHeader("timestamp");
        //String body = httpServletRequest.getHeader("body");
        //String sign = httpServletRequest.getHeader("sign");
        //if(!accessKey.equals("wkf")){
        //    throw new RuntimeException("您的签名有误");
        //}
        //String serverSign = SignUtils.genSign(body, "qwert");
        //if(!sign.equals(serverSign)){
        //    throw new RuntimeException("您的签名有误");
        //}
        return user;
    }


}
