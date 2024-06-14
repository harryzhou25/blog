package com.web.blogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.web.blogapi.dao.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class articleParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    private String title;

    private String content;

    private String summary;

    private Long authorId;

    private Long categoryId;

    private String contentHtml;

    private List<Tag> tags;
}
