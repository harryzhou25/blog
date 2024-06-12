package com.web.blogapi.dao.pojo;

import lombok.Data;

@Data
public class Comment {
    private Long id;

    private String content;

    private Long createDate;

    private Long authorId;

    private Long articleId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
