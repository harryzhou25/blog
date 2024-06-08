package com.web.blogapi.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.blogapi.dao.dos.Archivedo;
import com.web.blogapi.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface articleMapper extends BaseMapper<Article> {
    List<Archivedo> listArchives();
}
