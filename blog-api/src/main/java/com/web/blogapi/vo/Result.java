package com.web.blogapi.vo;

import lombok.Data;

@Data
public class Result {
    private int code;

    private String msg;

    private Object data;

    private boolean status;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.status = code == 200;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.status = code == 200;
    }

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result faild(int code, String msg) {
        return new Result(code, msg);
    }
}
