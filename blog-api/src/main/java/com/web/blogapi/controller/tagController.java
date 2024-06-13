package com.web.blogapi.controller;

import com.web.blogapi.vo.Result;
import com.web.blogapi.service.tagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tag")
public class tagController {
    @Autowired
    private tagService tagService;

    //Query top {num} popular tags
    @GetMapping("hot")
    public Result queryHotTags(Integer num){
        return tagService.queryHotTags(num);
    }

    @GetMapping()
    public Result queryAllTags(){
        return tagService.queryAllTags();
    }
}
