package com.web.blogapi.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Category {
    private Long id;

    private String avatar;

    private String category_name;

    @TableField("description")
    private String categoryDesc;
}
