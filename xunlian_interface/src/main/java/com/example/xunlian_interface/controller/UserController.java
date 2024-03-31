package com.example.xunlian_interface.controller;


import com.example.xunlian_client_sdk.model.User;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/name")
public class UserController {

    private final String IMAGES_DIRECTORY_PATH = "F:/images/";

    @GetMapping("/getName")
    public String getName(String name){
        return name;
    }

    @PostMapping("/postName")
    public User postName(@RequestBody User user, HttpServletRequest httpServletRequest){
        return user;
    }

    @PostMapping("/randomImages")
    public String getRandomImage() {
        List<String> images = getLocalImages();
        if (images.isEmpty()) {
            return "{\"error\": \"No images found.\"}";
        }

        Random random = new Random();
        String randomImage = images.get(random.nextInt(images.size()));
        String imagePath = IMAGES_DIRECTORY_PATH + randomImage;
        return "{\"imagePath\": \"" + imagePath + "\"}";
    }

    //获取本地图片列表
    private List<String> getLocalImages() {
        try {
            File imagesDirectory = ResourceUtils.getFile("F:/images");
            return Arrays.asList(imagesDirectory.list((dir, name) -> name.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif)$")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
