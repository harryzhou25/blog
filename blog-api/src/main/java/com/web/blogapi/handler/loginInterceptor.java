package com.web.blogapi.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.web.blogapi.vo.Result;
import com.web.blogapi.vo.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.web.blogapi.service.ssoService;
import com.web.blogapi.dao.pojo.sysUser;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class loginInterceptor implements HandlerInterceptor {
    @Autowired
    ssoService ssoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)){
            Result result = Result.faild(StatusCode.USER_NOT_LOGIN.getCode(), StatusCode.USER_NOT_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        sysUser sysUser = ssoService.verifyToken(token);
        if (sysUser == null){
            Result result = Result.faild(StatusCode.INVALID_TOKEN.getCode(), StatusCode.INVALID_TOKEN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        return true;
    }
}
