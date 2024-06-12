package com.web.blogapi.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.blogapi.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface commentMapper extends BaseMapper<Comment> {
}
