package com.cskaoyan.smzdm.dao;

import com.cskaoyan.smzdm.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */

@Mapper
public interface NewsMapper {
    List<News> showNews();

    Integer insert(News news);

    Integer incrLikeByNewId(String newsId);

    Integer reduceLikeByNewId(String newsId);

//    Long findNewsByNewsInfo(News news);

}
