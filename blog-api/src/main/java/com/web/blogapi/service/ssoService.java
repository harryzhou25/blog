package com.web.blogapi.service;

import com.web.blogapi.dao.pojo.sysUser;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.loginParam;
import com.web.blogapi.vo.registerParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ssoService {
    Result login(loginParam loginParam);

    Result logout(String token);

    Result register(registerParam registerParam);

    sysUser verifyToken(String token);
}
