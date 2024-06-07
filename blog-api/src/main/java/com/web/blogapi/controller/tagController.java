package com.web.blogapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.blogapi.dao.pojo.Tag;
import com.web.blogapi.dao.mapper.tagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tag")
public class tagController {
    @Autowired
    private tagMapper tagMapper;

    //Query top {num} popular tags
    @GetMapping("hot")
    public List<Tag> hot(int num){
        List<Long> ids =  tagMapper.selectHottest(num);
        List<Tag> tags = new ArrayList<>();
        for(Long id : ids){
            Tag tag = new Tag();
            tag.setId(id);
            QueryWrapper<Tag> wrapper = new QueryWrapper<>();
            wrapper.eq("tag_id", id);
            tag.setTagName(tagMapper.selectOne(wrapper).getTagName());
        }
        return tags;
    }
}
