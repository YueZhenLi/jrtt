package com.cskaoyan.smzdm.service;

import com.cskaoyan.smzdm.domain.User;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */
public interface UserService {
    User findUserById(int uid);

    User findUserByUsernameAndPassword(String username, String password);

    boolean register(String username, String password);
}
