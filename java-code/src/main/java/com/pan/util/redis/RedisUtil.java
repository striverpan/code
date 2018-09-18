package com.pan.util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by pan on 2017/11/1.
 */
public class RedisUtil {

    private static JedisPool pool;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config/redis");
        String address = bundle.getString("redis.addr");
        Integer port = Integer.valueOf(bundle.getString("redis.port"));
        Integer timeOut = Integer.valueOf(bundle.getString("redis.timeout"));
        String auth = bundle.getString("redis.auth");
        Integer maxIdle = Integer.valueOf(bundle.getString("redis.maxidle"));
        Integer maxActive = Integer.valueOf(bundle.getString("redis.maxactive"));
        Integer maxWait = Integer.valueOf(bundle.getString("redis.maxwait"));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);

        if (auth == null || "".equals(auth)) {
            pool = new JedisPool(jedisPoolConfig, address, port, timeOut);
        } else {
            pool = new JedisPool(jedisPoolConfig, address, port, timeOut, auth);
        }
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void main(String[] args) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            jedis.set("tutorial-name", "Redis tutorial");
            // Get the stored data and print it
            System.out.println("Stored string in redis:: " + jedis.hgetAll("rule_test"));
            System.out.println("Server is running: " + jedis.ping());
        }
    }

    public static void testIsConnected() {

        Jedis jedis = new Jedis("172.17.1.21");
        //check whether server is running or not
        System.out.println("Server is running: " + jedis.ping());

        jedis.set("tutorial-name", "Redis tutorial");
        // Get the stored data and print it
        System.out.println("Stored string in redis:: " + jedis.get("tutorial-name"));
    }
}
