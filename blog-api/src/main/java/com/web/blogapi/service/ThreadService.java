package com.web.blogapi.service;

import com.web.blogapi.dao.mapper.articleMapper;
import com.web.blogapi.dao.pojo.Article;

public interface ThreadService {
    void updateArticleCount(articleMapper articleMapper, Article article);
}
