package com.web.blogapi.service;

import com.web.blogapi.dao.pojo.sysUser;

public interface sysUserService {
    public sysUser getSysUserByid(Long id);

    public sysUser getSysUserByaccount(String account);

    public sysUser getUser(String account, String password);
}
