package com.cskaoyan.smzdm.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/14
 */
public class RedisUtil {
    static JedisPool pool;
    static Jedis jedis;


    static {
        pool = new JedisPool();
    }

    public static Jedis getJedis(){
        jedis = pool.getResource();
        return jedis;
    }

    public static String set(String key, String value){
        jedis = pool.getResource();
        String set = jedis.set(key, value);
        jedis.close();
        return  set;
    }

    public static String get (String key){
        jedis = pool.getResource();
        String s = jedis.get(key);
        jedis.close();
        return s;
    }

    public static Long sadd(String key, String... members){
        jedis = pool.getResource();
        Long sadd = jedis.sadd(key, members);
        jedis.close();
        return sadd;
    }

    public static boolean sismember(String key, String string){
        jedis = pool.getResource();
        Boolean sismember = jedis.sismember(key, string);
        jedis.close();
        return sismember;
    }

    public static Long srem(String key, String... strings){
        jedis = pool.getResource();
        Long srem = jedis.srem(key, strings);
        jedis.close();
        return srem;
    }

    public static Long scard(String key){
        jedis = pool.getResource();
        Long scard = jedis.scard(key);
        jedis.close();
        return scard;
    }
}
