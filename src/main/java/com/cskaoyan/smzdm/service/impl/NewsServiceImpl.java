package com.cskaoyan.smzdm.service.impl;

import com.cskaoyan.smzdm.dao.NewsMapper;
import com.cskaoyan.smzdm.dao.UserMapper;
import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.domain.User;
import com.cskaoyan.smzdm.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

//    UserMapper userMapper;

    @Override
    public List<News> showNews(){
        List<News> news = newsMapper.showNews();
        return news;
    }

    @Override
    public Integer addNews(News news) {
        Integer insert = newsMapper.insert(news);
        return insert;
    }
}
