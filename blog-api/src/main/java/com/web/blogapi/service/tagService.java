package com.web.blogapi.service;

import java.util.List;
import com.web.blogapi.dao.pojo.Tag;

public interface tagService {
    public List<Tag> queryTagsByArticleId(Long articleId);
}
