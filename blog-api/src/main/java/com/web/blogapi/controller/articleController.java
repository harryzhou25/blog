package com.web.blogapi.controller;

import java.util.List;

import com.web.blogapi.dao.dos.Archivedo;
import com.web.blogapi.vo.pageParam;
import com.web.blogapi.dao.pojo.Article;
import com.web.blogapi.service.articleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class articleController {

    @Autowired
    private articleService articleService;

    @PostMapping
    public List<Article> getArticleList(pageParam pageParam){
        return articleService.listArticlebyPage(pageParam);
    }

    @GetMapping("hot")
    public List<Article> getHotArticle(int num) {
        return articleService.selectHottest(num);
    }

    @GetMapping("new")
    public List<Article> getNewArticle(int num) {
        return articleService.selectNewest(num);
    }

    @GetMapping("archive")
    public List<Archivedo> getArchives() {
        return articleService.listArchives();
    }
}
