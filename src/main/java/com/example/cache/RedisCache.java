package com.example.cache;

import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class RedisCache implements Cache {

    private final ReadWriteLock readWriteLock
            = new ReentrantReadWriteLock();
    private String id;
    private static Jedis jedis = new Jedis("localhost");

    public RedisCache(final String id) {
        if (id == null)
            throw new IllegalArgumentException("Cache instances require an ID");
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public String toString() {
        return "Redis{" + id + '}';
    }

    @Override
    public void clear() {
        jedis.del(id.toString());
    }

    @Override
    public int getSize() {
        return jedis.hgetAll(id.toString().getBytes()).size();
    }

    //set和hset：set适合存储大的非结构化数据，而hset适合存放结构化数据
    //hset需要一个hash编码，我们用id做了这个hash码
    @Override
    public void putObject(final Object key, final Object value) {
        jedis.hset(id.toString().getBytes(), key.toString().getBytes(), SerializeUtil.serialize(value));
    }

    @Override
    public Object getObject(final Object key) {
        return SerializeUtil.unserialize(jedis.hget(id.toString().getBytes(), key.toString().getBytes()));
    }

    @Override
    public Object removeObject(final Object key) {
        return jedis.hdel(id.toString(), key.toString());
    }


}
