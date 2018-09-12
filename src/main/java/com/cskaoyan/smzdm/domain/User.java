package com.cskaoyan.smzdm.domain;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/11
 */
public class User {
    int id;
    String username;
    String passward;
    int age;
    String descr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passward='" + passward + '\'' +
                ", age=" + age +
                ", descr='" + descr + '\'' +
                '}';
    }
}
