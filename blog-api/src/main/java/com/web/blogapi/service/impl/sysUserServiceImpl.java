package com.web.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.blogapi.dao.pojo.sysUser;
import com.web.blogapi.service.sysUserService;
import com.web.blogapi.dao.mapper.sysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class sysUserServiceImpl implements sysUserService {

    @Autowired
    private sysUserMapper sysUserMapper;

    @Override
    public sysUser getSysUserByid(Long id) {
        return sysUserMapper.selectOne(new QueryWrapper<sysUser>().eq("id", id));
    }

    @Override
    public sysUser getSysUserByaccount(String account) {
        return sysUserMapper.selectOne(new QueryWrapper<sysUser>().eq("account", account));
    }

    public sysUser getUser(String account, String password) {
        return sysUserMapper.selectOne(new QueryWrapper<sysUser>().eq("account", account).eq("password", password));
    }
}
