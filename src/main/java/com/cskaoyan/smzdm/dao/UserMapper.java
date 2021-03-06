package com.cskaoyan.smzdm.dao;

import com.cskaoyan.smzdm.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/11
 */

@Mapper
public interface UserMapper {

      User findUserById(Integer id);

      User findUserByUsernameAndPassword(HashMap<Object, Object> hashMap);

      Integer findUserByUsernameCount(String username);

      Integer insert(User user);
}
