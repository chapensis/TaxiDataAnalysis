package com.yangchang.TaxiDataAnalysis.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisSetList {
    /**
     * @param args
     */
    public static void main(String[] args) {
        jedisSetDemo();
    }

    /**
     * 普通方式进行jedis操作
     */
    public static void jedisSetDemo() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.sadd("myset", "yangchang", "chenjiying");
        Set<String> setList =  jedis.smembers("myset");
        System.out.println("jedisSetDemo after sadd values: " + setList);

        boolean ycIsMember = jedis.sismember("myset","yangchang");
        boolean lyIsMember = jedis.sismember("myset","liyang");
        System.out.println("jedisSetDemo after yangchang sismember values: " + ycIsMember);
        System.out.println("jedisSetDemo after liyang sismember values: " + lyIsMember);

        jedis.sadd("myset2", "yangchang", "liyang");

        Set<String> setDiffList =  jedis.sdiff("myset2", "myset");
        System.out.println("jedisSetDemo after sdiff values: " + setDiffList);
        // sdiffstore v1 v2 v3 把v2,v3中相差的值存到v1

        Set<String> setInterList =  jedis.sinter("myset", "myset2");
        System.out.println("jedisSetDemo after sinter values: " + setInterList);

        Set<String> setUnionList =  jedis.sunion("myset", "myset2");
        System.out.println("jedisSetDemo after sunion values: " + setUnionList);

        long setLength = jedis.scard("myset");
        System.out.println("jedisSetDemo after scard values: " + setLength);

        String randMember = jedis.srandmember("myset");
        System.out.println("jedisSetDemo after srandmember values: " + randMember);
        jedis.close();
    }
}
