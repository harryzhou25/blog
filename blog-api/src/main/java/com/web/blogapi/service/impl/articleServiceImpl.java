package com.web.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.blogapi.dao.mapper.tagMapper;
import com.web.blogapi.dao.pojo.Article;
import com.web.blogapi.dao.pojo.Tag;
import com.web.blogapi.dao.pojo.articleBody;
import com.web.blogapi.service.ThreadService;
import com.web.blogapi.service.articleService;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.articleParam;
import com.web.blogapi.vo.pageParam;
import com.web.blogapi.dao.mapper.articleMapper;
import com.web.blogapi.dao.mapper.articleBodyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class articleServiceImpl implements articleService {

    @Autowired
    private articleMapper articleMapper;

    @Autowired
    private articleBodyMapper articleBodyMapper;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private tagMapper tagmapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // Paging query articles
    @Override
    public Result listArticlebyPage(pageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //Sort articles according to date and let top articles in the front of the array.
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        return Result.success(articlePage.getRecords());
    }

    @Override
    public Result listArchives() {
        return Result.success(articleMapper.listArchives());
    }

    @Override
    public Result selectHottest(int num) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //Sort articles according to date and let top articles in the front of the array.
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId, Article::getTitle);
        queryWrapper.last("limit " + num);
        return Result.success(articleMapper.selectList(queryWrapper));
    }

    @Override
    public Result selectNewest(int num) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //Sort articles according to date and let top articles in the front of the array.
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getId, Article::getTitle);
        queryWrapper.last("limit " + num);
        return Result.success(articleMapper.selectList(queryWrapper));
    }

    @Override
    public Result getBodyById(int id) {
        // update view count only when the frontend queries article body
        // avoid influencing the time cost of querying article body, I decided update the value using multithreading.

        String cacheKey = "Article_" + id;
        String cahce_string = redisTemplate.opsForValue().get(cacheKey);
        if (cahce_string != null) {
            Article article = JSON.parseObject(cahce_string, Article.class);
            threadService.updateArticleCount(articleMapper, article);
            return Result.success(cahce_string);
        }
        else {
            Article article = articleMapper.selectById(id);
            String article_json = JSON.toJSONString(article);
            articleBody articleBody = articleBodyMapper.selectById(article.getBodyId());

            redisTemplate.opsForValue().set(cacheKey, article_json, 1, TimeUnit.DAYS);

            threadService.updateArticleCount(articleMapper, article);
            return Result.success(articleBody);
        }
    }

    @Override
    public Result publishArticle(articleParam articleparam) {
        Article article = new Article();
        article.setWeight(0);
        article.setViewCounts(0);
        article.setCommentCounts(0);
        article.setTitle(articleparam.getTitle());
        article.setSummary(articleparam.getSummary());
        article.setAuthorId(articleparam.getAuthorId());
        article.setCreateDate(System.currentTimeMillis());
        article.setCategoryId(articleparam.getCategoryId());
        articleMapper.insert(article);
        Long articleId = article.getId();

        articleBody articleBody = new articleBody();
        articleBody.setArticleId(articleId);
        articleBody.setContent(articleparam.getContent());
        articleBody.setContent_html(articleparam.getContentHtml());
        articleBodyMapper.insert(articleBody);
        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        List<Tag> tags = articleparam.getTags();
        for (Tag tag : tags) {
            tag.setArticle_id(articleId);
            tagmapper.insert(tag);
        }
        return Result.success("Article published");
    }

    @Override
    public Result editArticle(articleParam articleparam) {
        Long articleId = articleparam.getArticleId();
        Article article = new Article();
        article.setId(articleId);
        article.setTitle(articleparam.getTitle());
        article.setSummary(articleparam.getSummary());
        article.setCategoryId(articleparam.getCategoryId());
        articleMapper.updateById(article);

        articleBody articlebody = new articleBody();
        articlebody.setArticleId(articleId);
        articlebody.setContent(articleparam.getContent());
        articlebody.setContent_html(articleparam.getContentHtml());
        LambdaUpdateWrapper<articleBody> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(articleBody::getArticleId, articleId);
        articleBodyMapper.update(articlebody, updateWrapper);

        List<Tag> tags = articleparam.getTags();
        List<Tag> current_tags = tagmapper.selectList(null);
        for (Tag tag : current_tags) {
            if(!tags.contains(tag)) {
                tagmapper.deleteById(tag.getId());
            }
        }
        for (Tag tag : tags) {
            tag.setArticle_id(articleId);
            LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Tag::getTagName, tag.getTagName());
            queryWrapper.eq(Tag::getArticle_id, articleId);
            Tag _tag = tagmapper.selectOne(queryWrapper);
            if(_tag == null) {
                tagmapper.insert(tag);
            }
        }
        return Result.success("Article updated");
    }
}
