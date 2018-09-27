package com.cskaoyan.smzdm.service.impl;

import com.cskaoyan.smzdm.dao.NewsMapper;
import com.cskaoyan.smzdm.dao.UserMapper;
import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.domain.User;
import com.cskaoyan.smzdm.service.NewsService;
import com.cskaoyan.smzdm.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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

    @Override
    public boolean incrLikeByNewId(String newsId, User user) {
        int id = user.getId();
        String idLike = newsId+"Like";
        String idDisike = newsId+"DisLike";
        String uid = String.valueOf(id);
        //判断此新闻的点赞集合中是否有该用户，没有则添加该用户，有则不做操作
        Boolean sismember = RedisUtil.sismember(idLike, uid);
        Long sadd = null;
        Long srem = null;
        if (!sismember){
            sadd = RedisUtil.sadd(idLike, uid);
            Integer integer = newsMapper.incrLikeByNewId(newsId);
            //若踩的列表中存在该用户，则将其移除
            if (RedisUtil.sismember(idDisike, uid)){
                srem = RedisUtil.srem(idDisike, uid);
            }
        }
        return sadd!=null?true:false;
    }

    @Override
    public boolean reduceLikeByNewId(String newsId, User user) {
        int id = user.getId();
        String idLike = newsId+"Like";
        String idDisike = newsId+"DisLike";
        String uid = String.valueOf(id);
        //若踩的列表中是否存在该用户，没有则添加，有则不做操作
        Boolean sismember = RedisUtil.sismember(idDisike, uid);
        Long sadd = null;
        Long srem = null;
        if (!sismember){
            sadd = RedisUtil.sadd(idDisike, uid);
            Integer integer = newsMapper.reduceLikeByNewId(newsId);
            //判断此新闻的点赞集合中是否有该用户，有则移除该用户
            if (RedisUtil.sismember(idLike, uid)){
                srem = RedisUtil.srem(idLike, uid);
            }
        }
        return sadd!=null?true:false;
    }

   /* @Override
    public News findNewsByNewsInfo(News news) {
        newsMapper.findNewsByNewsInfo(news);
        return null;
    }*/
}
