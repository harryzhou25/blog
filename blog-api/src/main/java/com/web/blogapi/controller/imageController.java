package com.web.blogapi.controller;

import com.web.blogapi.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("image")
public class imageController {
    @PostMapping
    public Result uploadImage(@RequestParam("image") MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String final_name = UUID.randomUUID().toString() + suffix;
        return Result.success("Image uploaded");
    }
}
