package com.web.blogapi.vo;

import com.web.blogapi.dao.pojo.Category;
import com.web.blogapi.dao.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class articleVo {
    private Long articleId;

    private String title;

    private String content;

    private String summary;

    private Integer weight;

    private String createDate;

    private String author;

    private List<Tag> tags;

    private Category category;
}
