package com.cskaoyan.smzdm.domain;

import java.util.Date;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */
public class News {
    int id;
    String image;
    Date createdDate;
    String title;
    String link;
    int commentCount;
    int likeCount;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    int uid;

    public News() {
    }

    public News(int id, String image, Date createdDate, String title, String link, int commentCount, int likeCount) {
        this.id = id;
        this.image = image;
        this.createdDate = createdDate;
        this.title = title;
        this.link = link;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", createdDate=" + createdDate +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", uid=" + uid +
                '}';
    }
}
