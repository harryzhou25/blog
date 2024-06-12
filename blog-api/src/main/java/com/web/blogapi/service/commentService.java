package com.web.blogapi.service;

import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.commentParam;

public interface commentService {
    Result getCommentsByAId(Long aid);

    Result getCommentsByPid(Long pid);

    Result insertComment(commentParam commentparam);
}
