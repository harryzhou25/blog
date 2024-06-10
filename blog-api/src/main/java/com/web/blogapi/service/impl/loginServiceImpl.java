package com.web.blogapi.service.impl;

import com.web.blogapi.dao.pojo.sysUser;
import com.web.blogapi.service.loginService;
import com.web.blogapi.service.sysUserService;
import com.web.blogapi.util.JWTUtil;
import com.web.blogapi.vo.loginParam;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService {
    @Autowired
    sysUserService userService;

    private static final String salt = "saltlt%#$*";

    @Override
    public String login(loginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if(StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(password)){
            return "";
        }
        String pwd = DigestUtils.md5Hex(password + salt);
        sysUser user = userService.getUser(account, pwd);
        if(user == null){
            return "";
        }
        return JWTUtil.createToken(user.getId());
    }
}
