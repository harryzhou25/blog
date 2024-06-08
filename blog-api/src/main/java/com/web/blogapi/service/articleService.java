package com.web.blogapi.service;

import com.web.blogapi.dao.dos.Archivedo;
import com.web.blogapi.vo.pageParam;
import com.web.blogapi.dao.pojo.Article;

import java.util.List;

public interface articleService {
    List<Article> listArticlebyPage(pageParam pageParam);

    List<Archivedo> listArchives();

    List<Article> selectHottest(int num);

    List<Article> selectNewest(int num);

}
