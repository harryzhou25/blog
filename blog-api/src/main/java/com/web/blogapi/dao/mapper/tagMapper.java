package com.web.blogapi.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.blogapi.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface tagMapper extends BaseMapper<Tag> {

    List<Tag> selectTagByArticleId(Long article_id);

    List<Long> selectHottest(int num);
}
