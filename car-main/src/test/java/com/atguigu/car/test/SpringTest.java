package com.atguigu.car.test;

import com.atguigu.sys.bean.SysMenu;
import com.atguigu.sys.mapper.SysMenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Iterator;
import java.util.Set;

@ContextConfiguration(locations={"classpath:spring/spring-bean.xml",
        "classpath:spring/spring-mybatis.xml","classpath:spring/spring-tx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    /**

    */
    @Test
    public void test () {
//        Jedis jedis = new Jedis("192.168.237.129", 6659);
//        String setex = jedis.setex("phone", 120, "code");
////        Long zadd = jedis.zadd("k10", 100, "a");
//        Set<Tuple> k10 = jedis.zrangeWithScores("k10", 0, -1);
//        for (Tuple tuple : k10) {
//            System.out.println(tuple.getElement() + "=" + tuple.getScore());
//        }
////        Set<String> k10 = jedis.zrange("k10", 0, -1);
////        for (String s : k10) {
////            System.out.println(s);
////        }
//        System.out.println("setex = " + setex);
//        System.out.println(jedis.ping());

    }

    /**

    */
    @Test
    public void test02 () {
//        Jedis jedis = new Jedis("139.224.132.5", 6659);
//        String ping = jedis.ping();
//        System.out.println(ping);
    }

}
