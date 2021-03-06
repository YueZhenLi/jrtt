package com.cskaoyan.smzdm.service.impl;

import com.cskaoyan.smzdm.dao.UserMapper;
import com.cskaoyan.smzdm.domain.User;
import com.cskaoyan.smzdm.service.UserService;
import com.cskaoyan.smzdm.util.RandomNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(int uid) {
        User userById = userMapper.findUserById(uid);
        return userById;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("name",username);
        hashMap.put("password",password);
        User userByUsernameAndPassword = userMapper.findUserByUsernameAndPassword(hashMap);
        return userByUsernameAndPassword;
    }

    @Override
    public boolean register(String username, String password) {

        if (userMapper.findUserByUsernameCount(username)>0){
            return false;
        }
        User user = new User();
        int randomNum = RandomNum.getRandomNum(11);
        String imgUrl = String.valueOf(randomNum)+".jpg";
        try {
            File file = ResourceUtils.getFile("classpath:static/images/img/"+imgUrl);
            user.setHeadUrl(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        user.setName(username);
        user.setPassword(password);
        Integer insert = userMapper.insert(user);
        if (insert==1){
            return true;
        }
        return false;
    }

}
