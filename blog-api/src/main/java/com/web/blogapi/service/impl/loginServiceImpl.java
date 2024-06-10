package com.web.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.web.blogapi.vo.StatusCode;
import com.web.blogapi.dao.pojo.sysUser;
import com.web.blogapi.service.loginService;
import com.web.blogapi.service.sysUserService;
import com.web.blogapi.util.JWTUtil;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.loginParam;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class loginServiceImpl implements loginService {
    @Autowired
    sysUserService userService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String salt = "saltlt%#$*";

    @Override
    public Result login(loginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if(StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(password)){
            return Result.faild(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }
        String pwd = DigestUtils.md5Hex(password + salt);
        sysUser user = userService.getUser(account, pwd);
        if(user == null){
            return Result.faild(StatusCode.ACCOUNT_PWD_NOT_EXIST.getCode(), StatusCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtil.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }
}