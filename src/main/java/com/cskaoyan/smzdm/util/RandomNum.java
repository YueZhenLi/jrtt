package com.cskaoyan.smzdm.util;

import java.util.Random;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/15
 */
public class RandomNum {

    public static int getRandomNum(int i){
        Random random = new Random();
        int anInt = random.nextInt(i);
        return anInt;
    }
}
