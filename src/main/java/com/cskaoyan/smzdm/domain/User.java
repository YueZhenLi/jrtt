package com.cskaoyan.smzdm.domain;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/11
 */
public class User {
    int id;
    String name;
    String headUrl;
    String password;

    public User() {
    }

    public User(int id, String name, String headUrl, String password) {
        this.id = id;
        this.name = name;
        this.headUrl = headUrl;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
