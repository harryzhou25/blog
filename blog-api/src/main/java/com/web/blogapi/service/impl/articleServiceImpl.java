package com.web.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.blogapi.dao.dos.Archivedo;
import com.web.blogapi.dao.pojo.Article;
import com.web.blogapi.dao.pojo.articleBody;
import com.web.blogapi.service.ThreadService;
import com.web.blogapi.service.articleService;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.pageParam;
import com.web.blogapi.dao.mapper.articleMapper;
import com.web.blogapi.dao.mapper.articleBodyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class articleServiceImpl implements articleService {

    @Autowired
    private articleMapper articleMapper;

    @Autowired
    private articleBodyMapper articleBodyMapper;

    @Autowired
    private ThreadService threadService;

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

    @Override
    public List<Archivedo> listArchives() {
        return articleMapper.listArchives();
    }

    @Override
    public List<Article> selectHottest(int num) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //Sort articles according to date and let top articles in the front of the array.
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId, Article::getTitle);
        queryWrapper.last("limit " + num);
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Article> selectNewest(int num) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //Sort articles according to date and let top articles in the front of the array.
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getId, Article::getTitle);
        queryWrapper.last("limit " + num);
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public Result getBodyById(int id) {
        Article article = articleMapper.selectById(id);
        articleBody articleBody = articleBodyMapper.selectById(article.getBodyId());
        // update view count only when the frontend queries article body
        // avoid influencing the time cost of querying article body, I decided update the value using multithreading.
        // TODO: Change this update by using redis.
        threadService.updateArticleCount(articleMapper, article);
        return Result.success(articleBody);
    }
}
