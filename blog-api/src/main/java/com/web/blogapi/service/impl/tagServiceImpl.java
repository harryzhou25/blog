package com.web.blogapi.service.impl;

import com.web.blogapi.dao.mapper.tagMapper;
import com.web.blogapi.dao.pojo.Tag;
import com.web.blogapi.service.tagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tagServiceImpl implements tagService {

    @Autowired
    private tagMapper tagMapper;

    public List<Tag> queryTagsByArticleId(Long articleId) {
        return tagMapper.selectTagByArticleId(articleId);
    }
}
