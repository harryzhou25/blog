package com.web.blogapi.service;

import java.util.List;
import com.web.blogapi.dao.pojo.Tag;
import com.web.blogapi.vo.Result;

public interface tagService {
    public List<Tag> queryTagsByArticleId(Long articleId);

    public Result queryHotTags(Integer num);

    public Result queryAllTags();
}
