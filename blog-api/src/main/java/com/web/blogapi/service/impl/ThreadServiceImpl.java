package com.web.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.web.blogapi.dao.mapper.articleMapper;
import com.web.blogapi.dao.pojo.Article;
import com.web.blogapi.service.ThreadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadServiceImpl implements ThreadService {
    @Override
    @Async("DB_tasks")
    public void updateArticleCount(articleMapper articleMapper, Article article) {
        int pre_cnt = article.getViewCounts();
        Article new_article = new Article();
        new_article.setViewCounts(pre_cnt + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        updateWrapper.eq(Article::getViewCounts, article.getViewCounts());
        articleMapper.update(new_article, updateWrapper);
    }
}
