package com.web.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.web.blogapi.dao.mapper.articleMapper;
import com.web.blogapi.dao.pojo.Article;
import com.web.blogapi.dao.pojo.Comment;
import com.web.blogapi.vo.Result;
import com.web.blogapi.service.commentService;
import com.web.blogapi.dao.mapper.commentMapper;
import com.web.blogapi.vo.commentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class commentServiceImpl implements commentService {

    @Autowired
    commentMapper commentmapper;

    @Autowired
    articleMapper articlemapper;

    @Override
    public Result getCommentsByAId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        queryWrapper.eq(Comment::getLevel, 1);
        queryWrapper.orderByDesc(Comment::getCreateDate);
        List<Comment> comments = commentmapper.selectList(queryWrapper);
        return Result.success(comments);
    }

    @Override
    public Result getCommentsByPid(Long pid) {
        if(pid == 0) {
            return Result.success(new ArrayList<>());
        }
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, pid);
        queryWrapper.orderByDesc(Comment::getCreateDate);
        List<Comment> res = commentmapper.selectList(queryWrapper);
        return Result.success(res);
    }

    @Override
    public Result insertComment(commentParam commentparam) {
        Comment comment = new Comment();
        comment.setArticleId(commentparam.getArticleId());
        comment.setContent(commentparam.getContent());
        comment.setToUid(commentparam.getToUid());
        comment.setParentId(commentparam.getParentId());
        comment.setCreateDate(System.currentTimeMillis());
        if(commentparam.getParentId() == 0) {
            comment.setLevel(1);
        }
        else {
            comment.setLevel(2);
        }
        Article article = articlemapper.selectOne(new LambdaQueryWrapper<Article>().eq(Article::getId, commentparam.getArticleId()));
        comment.setAuthorId(article.getAuthorId());
        commentmapper.insert(comment);
        return Result.success("Comment updated");
    }

    @Override
    public Result deleteComment(Long id) {
        commentmapper.deleteById(id);
        return Result.success("Comment deleted");
    }
}
