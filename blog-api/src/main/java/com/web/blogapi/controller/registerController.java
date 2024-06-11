package com.web.blogapi.controller;

import com.web.blogapi.service.ssoService;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.registerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class registerController {
    @Autowired
    ssoService ssoService;

    @PostMapping
    public Result register(registerParam registerparam) {
        return ssoService.register(registerparam);
    }
}
