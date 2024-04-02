package com.example.xunlian_interface.controller;


import com.example.xunlian_client_sdk.model.User;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/name")
public class UserController {

    private final String IMAGES_DIRECTORY_PATH = "localhost:8181/api";

    @GetMapping("/getName")
    public String getName(String name) {
        return name;
    }

    @PostMapping("/postName")
    public User postName(@RequestBody User user, HttpServletRequest httpServletRequest) {
        return user;
    }

    @PostMapping("/randomImages")
    public String getRandomImage() {
        List<String> images = getLocalImages();
        if (images.isEmpty()) {
            return "{\"error\": \"No images found.\"}";
        }

        Random random = new Random();
        String randomImageName = images.get(random.nextInt(images.size()));
        return "{\"imagePath\": \"" + randomImageName + "\"}";
    }

    //获取本地图片列表
    private List<String> getLocalImages() {
        try {
            File imagesDirectory = ResourceUtils.getFile("classpath:static");
            File[] files = imagesDirectory.listFiles((dir, name) -> name.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif)$"));
            if (files != null) {
                List<String> imageNames = new ArrayList<>();
                for (File file : files) {
                    imageNames.add(file.getName());
                }
                return imageNames;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}