package com.web.blogapi.controller;

import com.web.blogapi.service.commentService;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.commentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class commentController {
    @Autowired
    commentService commentservice;

    @GetMapping("articleId/{id}")
    public Result getCommentsByAid(@PathVariable Long id) {
        return commentservice.getCommentsByAId(id);
    }

    @GetMapping("parentId/{id}")
    public Result getCommentsByPid(@PathVariable Long id) {
        return commentservice.getCommentsByPid(id);
    }

    @PostMapping("publish")
    public Result insertComment(commentParam commentparam) {
        return commentservice.insertComment(commentparam);
    }

    @GetMapping("delete/{id}")
    public Result deleteComment(@PathVariable Long id) {
        return commentservice.deleteComment(id);
    }
}
