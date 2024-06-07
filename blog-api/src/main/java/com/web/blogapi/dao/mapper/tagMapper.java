package com.web.blogapi.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.blogapi.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface tagMapper extends BaseMapper<Tag> {

    @Select("SELECT * from tag where article_id = #{article_id}")
    public List<Tag> selectTagByArticleId(Long article_id);
}
