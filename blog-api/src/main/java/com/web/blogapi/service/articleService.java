package com.web.blogapi.service;

import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.articleParam;
import com.web.blogapi.vo.pageParam;

public interface articleService {
    Result listArticlebyPage(pageParam pageParam);

    Result listArchives();

    Result selectHottest(int num);

    Result selectNewest(int num);

    Result getBodyById(int id);

    Result publishArticle(articleParam articleparam);

    Result editArticle(articleParam articleparam);
}
