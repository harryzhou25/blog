package com.web.blogapi.vo;

public enum StatusCode {
    // Generic Status Code
    PARAMS_ERROR(10001,"Incorrect Parameters"),

    // SSO Status Code
    ACCOUNT_PWD_NOT_EXIST(10011,"Incorrect Account number or Password"),
    TOKEN_ERROR(10012,"Invalid token"),
    ACCOUNT_EXIST(10013, "Account already exists");

    private int code;
    private String msg;

    StatusCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
