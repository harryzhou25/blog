package com.web.blogapi.dao.pojo;

import lombok.Data;

@Data
public class Tag {

    private Long id;

    private Long article_id;

    private String tagName;
}
