package com.web.blogapi.dao.pojo;

import lombok.Data;

@Data
public class articleBody {
    private Long id;

    private Long ArticleId;

    private String content;

    private String content_html;
}
