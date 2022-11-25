package com.cache.cachefly.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Steven
 * @date 2022年11月21日 1:28
 */
public class CacheUtil {
   static RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

   private LinkedBlockingDeque list=new LinkedBlockingDeque();

    public static void main(String[] args) throws InterruptedException {


        update(99);


    }

    private static void update(int i) {

        Boolean delete = redisTemplate.delete("100");
        if (delete){
            updateDb();
        }


    }

    private static void updateDb() {

    }

    private static String findUserById(int i) throws InterruptedException{
        String userById = null;

        Object o = redisTemplate.opsForValue().get("100");
        if (o == null) {
            Thread.sleep(500);
            o = redisTemplate.opsForValue().get("100");
            if (o == null) {
                userById = findUserById(100);
            }
        }
        return userById;
    }


}
