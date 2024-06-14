package com.web.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.web.blogapi.vo.StatusCode;
import com.web.blogapi.dao.pojo.sysUser;
import com.web.blogapi.service.ssoService;
import com.web.blogapi.service.sysUserService;
import com.web.blogapi.util.JWTUtil;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.loginParam;
import com.web.blogapi.vo.registerParam;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ssoServiceImpl implements ssoService {
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

    @Override
    public Result register(registerParam registerParam) {
        if(StringUtil.isNullOrEmpty(registerParam.getAccount())
            || StringUtil.isNullOrEmpty(registerParam.getPassword())
            || StringUtil.isNullOrEmpty(registerParam.getName())){
            return Result.faild(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }
        sysUser tmp = userService.getUserByaccount(registerParam.getAccount());
        if(tmp != null){
            return Result.faild(StatusCode.ACCOUNT_EXIST.getCode(), StatusCode.ACCOUNT_EXIST.getMsg());
        }
        sysUser user = new sysUser();
        user.setAccount(registerParam.getAccount());
        user.setPassword(DigestUtils.md5Hex(registerParam.getPassword() + salt));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setDeleted(0);
        user.setAdmin(0);

        userService.insertUser(user);

        String token = JWTUtil.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public sysUser verifyToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtil.checkToken(token);
        if (stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        return JSON.parseObject(userJson, sysUser.class);
    }
}