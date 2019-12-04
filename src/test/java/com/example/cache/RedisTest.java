package com.example.cache;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {

    @Test
    public void test() {
        Jedis jedis=new Jedis("localhost");
        jedis.set("foo","bar");
        System.out.println(jedis.get("foo"));
    }
}
