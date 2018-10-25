package com.yangchang.TaxiDataAnalysis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        jedisDemo1();
        jedisDemo2();
        jedisStringDemo();
        jedisHashDemo();
    }

    /**
     * 普通方式进行jedis操作
     */
    public static void jedisDemo1() {
        // 1、设置IP地址和端口
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 2、保存数据
        jedis.set("name", "yangchang");

        // 3、获取数据
        String value = jedis.get("name");
        System.out.println("jedisDemo1 value: " + value);

        // 4、关闭连接
        jedis.close();
    }

    /**
     * 连接池的方式操作jedis
     */
    public static void jedisDemo2() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(3);

        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            // 2、保存数据
            jedis.set("name2", "chenjieying");

            // 3、获取数据
            String value = jedis.get("name");
            System.out.println("jedisDemo2 value: " + value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }

            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }

    /**
     * 普通方式进行jedis String操作
     */
    public static void jedisStringDemo() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("num", "1");
        String value = jedis.get("num");
        System.out.println("jedisStringDemo set value: " + value);

        jedis.incr("num");
        value = jedis.get("num");
        System.out.println("jedisStringDemo after incr value: " + value);

        jedis.decr("num");
        value = jedis.get("num");
        System.out.println("jedisStringDemo after decr value: " + value);

        jedis.incrBy("num", 5);
        value = jedis.get("num");
        System.out.println("jedisStringDemo after incrBy 5 value: " + value);

        jedis.decrBy("num", 5);
        value = jedis.get("num");
        System.out.println("jedisStringDemo after decrBy 5 value: " + value);

        jedis.append("num", "33");
        value = jedis.get("num");
        System.out.println("jedisStringDemo after append 33 value: " + value);

        jedis.close();
    }

    /**
     * 普通方式进行jedis hash操作
     */
    public static void jedisHashDemo() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.hset("user", "name", "yangchang");

        String value = jedis.hget("user", "name");
        System.out.println("jedisHashDemo after hset name: " + value);

        Map<String, String> map = new HashMap<>();
        map.put("name", "chenjieying");
        map.put("age", "21");
        jedis.hmset("user", map);

        value = jedis.hget("user", "name");
        System.out.println("jedisHashDemo after hmset value: " + value);

        List<String> values = jedis.hmget("user", "name", "age");
        System.out.println("jedisHashDemo hmget values: " + values);

        Map<String, String> allHash = jedis.hgetAll("user");
        System.out.println("jedisHashDemo hgetAll values: " + allHash);

        jedis.hincrBy("user", "age", 10);
        allHash = jedis.hgetAll("user");
        System.out.println("jedisHashDemo hincrBy values: " + allHash);

        boolean isExsitName = jedis.hexists("user", "name");
        System.out.println("jedisHashDemo isExsitName values: " + isExsitName);

        boolean isExsitPassword = jedis.hexists("user", "password");
        System.out.println("jedisHashDemo isExsitPassword values: " + isExsitPassword);

        Long hLength = jedis.hlen("user");
        System.out.println("jedisHashDemo hLength values: " + hLength);

        Set<String> hkeys = jedis.hkeys("user");
        System.out.println("jedisHashDemo hash keys: " + hkeys);

        List<String> hvalues = jedis.hvals("user");
        System.out.println("jedisHashDemo hash values: " + hvalues);

    }
}
