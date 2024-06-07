package com.web.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.blogapi.dao.pojo.Article;
import com.web.blogapi.service.articleService;
import com.web.blogapi.vo.pageParam;
import com.web.blogapi.dao.mapper.articleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class articleServiceImpl implements articleService {

    @Autowired
    private articleMapper articleMapper;

    // Paging query articles
    @Override
    public List<Article> listArticlebyPage(pageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //Sort articles according to date and let top articles in the front of the array.
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        return articlePage.getRecords();
    }
}
