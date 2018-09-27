package com.cskaoyan.smzdm.service;

import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.domain.User;

import java.util.List;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */
public interface NewsService {
    List<News> showNews();

    Integer addNews(News news);

    boolean incrLikeByNewId(String newsId, User user);

    boolean reduceLikeByNewId(String newsId, User user);

//    News findNewsByNewsInfo(News news);
}
