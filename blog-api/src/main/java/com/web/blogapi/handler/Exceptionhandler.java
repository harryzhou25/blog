package com.web.blogapi.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Exceptionhandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {
        e.printStackTrace();
        return "System abnormality";
    }
}
