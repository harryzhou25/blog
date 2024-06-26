package com.web.blogapi.controller;

import com.web.blogapi.service.ssoService;
import com.web.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class logoutController {
    @Autowired
    ssoService ssoservice;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token) {
        return ssoservice.logout(token);
    }
}
