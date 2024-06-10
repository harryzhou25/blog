package com.web.blogapi.service;

import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.loginParam;

public interface loginService {
    public Result login(loginParam loginParam);

    public Result logout(String token);
}
