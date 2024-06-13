package com.web.blogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class commentParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long commentId;

    private Long articleId;

    private Long parentId;

    private Long toUid;

    private String content;
}
