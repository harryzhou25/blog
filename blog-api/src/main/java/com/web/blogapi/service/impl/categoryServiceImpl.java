package com.web.blogapi.service.impl;

import com.web.blogapi.vo.Result;
import com.web.blogapi.dao.pojo.Category;
import com.web.blogapi.service.categoryService;
import com.web.blogapi.dao.mapper.categoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {
    @Autowired
    categoryMapper categoryMapper;

    @Override
    public Result getCategoryById(int id) {
        Category category = categoryMapper.selectById(id);
        return Result.success(category);
    }

    @Override
    public Result getAllCategory() {
        List<Category> res = categoryMapper.selectList(null);
        return Result.success(res);
    }
}
