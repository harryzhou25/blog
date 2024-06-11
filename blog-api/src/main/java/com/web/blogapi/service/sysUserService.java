package com.web.blogapi.service;

import com.web.blogapi.dao.pojo.sysUser;

public interface sysUserService {
    sysUser getSysUserByid(Long id);

    sysUser getUserByaccount(String account);

    sysUser getUser(String account, String password);

    void insertUser(sysUser user);
}
