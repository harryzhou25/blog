package com.web.blogapi.vo;

import lombok.Data;

import java.util.List;

@Data
public class articleParam {
    private String title;

    private String content;

    private String summary;

    private Long authorId;

    private Long categoryId;

    private String contentHtml;

    private List<String> tags;
}
