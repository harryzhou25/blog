package com.web.blogapi.service;

import com.web.blogapi.vo.pageParam;
import com.web.blogapi.dao.pojo.Article;

import java.util.List;

public interface articleService {
    public List<Article> listArticlebyPage(pageParam pageParam);
}
