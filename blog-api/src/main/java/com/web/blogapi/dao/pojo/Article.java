package com.web.blogapi.dao.pojo;

import lombok.Data;

@Data
public class Article {

    public static final int Article_TOP = 0;

    public static final int Article_Common = 0;

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Long authorId;

    private Long bodyId;

    private Long categoryId;

    private Integer weight;

    private Long createDate;
}
