package com.web.blogapi.controller;

import com.web.blogapi.vo.loginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class loginController {

    @PostMapping
    public boolean login(loginParam loginParam) {
        return true;
    }
}
