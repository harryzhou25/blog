package com.web.blogapi.controller;


import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.pageParam;
import com.web.blogapi.vo.articleParam;
import com.web.blogapi.service.articleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class articleController {

    @Autowired
    private articleService articleService;

    /**
     * Query articles by page
     * @param pageParam the page parameter
     * @return Result
     */
    @PostMapping
    public Result getArticleList(pageParam pageParam){
        return articleService.listArticlebyPage(pageParam);
    }

    /**
     * Query the hottest 'num' articles
     * @param num The number of articles
     * @return Result
     */
    @GetMapping("hot")
    public Result getHotArticle(int num) {
        return articleService.selectHottest(num);
    }

    /**
     * Query the newest 'num' articles
     * @param num The number of articles
     * @return Result
     */
    @GetMapping("new")
    public Result getNewArticle(int num) {
        return articleService.selectNewest(num);
    }

    /**
     * Query all the archives according to create date
     * @return Result
     */
    @GetMapping("archive")
    public Result getArchives() {
        return articleService.listArchives();
    }

    /**
     * Query article body through article id
     * @param id The article id
     * @return Result contains articleBody
     */
    @GetMapping("body/{id}")
    public Result getBodyById(@PathVariable int id) {
        return articleService.getBodyById(id);
    }

    @PostMapping("publish")
    public Result publishArticle(@RequestBody articleParam articleparam) {
        return articleService.publishArticle(articleparam);
    }

    @PostMapping("edit")
    public Result editArticle(@RequestBody articleParam articleparam) {
        return articleService.editArticle(articleparam);
    }
}
