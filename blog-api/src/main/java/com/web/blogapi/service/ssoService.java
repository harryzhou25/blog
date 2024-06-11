package com.web.blogapi.service;

import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.loginParam;

public interface ssoService {
    Result login(loginParam loginParam);

    Result logout(String token);
}
