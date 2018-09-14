package com.cskaoyan.smzdm.domain.vo;

import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.domain.User;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */
public class VO {
    int  like = 0;
    News news;
    User user;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
