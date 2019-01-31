//package com.gq.redis_pool;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.gq.common.ReadPropertiesUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import redis.clients.jedis.Jedis;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestGetProperties {
//
////    @Autowired
//    ReadPropertiesUtil properties;
//
//    @Autowired
//    RedisClient redisClient;
//
//
//    public void proTest() throws JsonProcessingException {
//        System.out.println("properties: "+properties);
//        System.out.println("Host: "+properties.getHost());
//        System.out.println("port: "+properties.getPort());
//        System.out.println("MaxIdle: "+properties.getMaxIdle());
//    }
//
//    @Test
//    public void redisTest(){
////        redisClient.set("fuck","jjjj");
////        System.out.println("redis: " + redisClient.get("fuck"));
//        for(int i = 0;i < 100; i++){
//            TestThread thread = new TestThread(i,redisClient);
//            thread.start();
//        }
//    }
//}
//class TestThread extends Thread {
//
//    RedisClient redisClient;
//    int i = 0;
//    public TestThread (int i,RedisClient redisClient){
//        this.i = i;
//        this.redisClient = redisClient;
//    }
//
//    public void run(){
//        Jedis jedis = redisClient.getJedis();
//        Date date = new Date();
//        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        String time = SimpleDateFormat.format(date);
//        jedis.set("key"+i,time);
//        try {
//            //每次睡眠一个随机时间
//            Thread.sleep((int)(Math.random()*5000));
//            String foo = jedis.get("key"+i);
//            System.out.println("【输出>>>>】key:" + foo + " 第:"+i+"个线程");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            jedis.close();
//        }
//    }
//}
