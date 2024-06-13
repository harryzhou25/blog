package com.web.blogapi.controller;

import com.web.blogapi.vo.Result;
import com.web.blogapi.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class categoryController {

    @Autowired
    categoryService categoryService;

    @GetMapping("{id}")
    public Result getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public Result getAllCategory() {
        return categoryService.getAllCategory();
    }
}
