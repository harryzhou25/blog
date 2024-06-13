package com.web.blogapi.vo;

import lombok.Data;

@Data
public class commentParam {
    private Long commentId;

    private Long articleId;

    private Long parentId;

    private Long toUid;

    private String content;
}
