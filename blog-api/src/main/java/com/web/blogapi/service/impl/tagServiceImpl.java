package com.web.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.blogapi.dao.mapper.tagMapper;
import com.web.blogapi.dao.pojo.Tag;
import com.web.blogapi.service.tagService;
import com.web.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class tagServiceImpl implements tagService {

    @Autowired
    private tagMapper tagMapper;

    public List<Tag> queryTagsByArticleId(Long articleId) {
        return tagMapper.selectTagByArticleId(articleId);
    }

    @Override
    public Result queryHotTags(Integer num) {
        List<Long> ids = tagMapper.selectHottest(num);
        List<Tag> tags = new ArrayList<>();
        for(Long id : ids){
            Tag tag = new Tag();
            tag.setId(id);
            QueryWrapper<Tag> wrapper = new QueryWrapper<>();
            wrapper.eq("tag_id", id);
            tag.setTagName(tagMapper.selectOne(wrapper).getTagName());
        }
        return Result.success(tags);
    }

    @Override
    public Result queryAllTags() {
        List<Tag> res = tagMapper.selectList(null);
        return Result.success(res);
    }
}
