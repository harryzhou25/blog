package com.web.blogapi.dao.mapper;

import com.web.blogapi.dao.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface categoryMapper extends BaseMapper<Category> {
}
