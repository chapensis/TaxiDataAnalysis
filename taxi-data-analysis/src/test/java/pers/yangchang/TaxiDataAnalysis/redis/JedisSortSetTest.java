package pers.yangchang.TaxiDataAnalysis.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisSortSetTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        jedisSortSetDemo();
    }

    public static void jedisSortSetDemo() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.zadd("mysetsort", 100, "yangchang");
        jedis.zadd("mysetsort", 90, "chenjieying");
        jedis.zadd("mysetsort", 80, "liyang");
        double score = jedis.zscore("mysetsort", "yangchang");
        System.out.println("jedisSetDemo after zadd yangchang score: " + score);

        long num = jedis.zcard("mysetsort");
        System.out.println("jedisSetDemo after zcard values: " + num);

        Set<String> setSortSet = jedis.zrange("mysetsort", 0, -1);
        System.out.println("jedisSetDemo after zrange values: " + setSortSet);

        setSortSet = jedis.zrevrange("mysetsort", 0, -1);
        System.out.println("jedisSetDemo after zrevrange values: " + setSortSet);

        long result = jedis.zremrangeByRank("mysetsort", 0, 1);
        System.out.println("jedisSetDemo after zremrangeByRank 0-1 values: " + result);

        setSortSet = jedis.zrangeByScore("mysetsort", 79, 101);
        System.out.println("jedisSetDemo after zrangeByScore 79-101 values: " + setSortSet);

        double resul2 = jedis.zincrby("mysetsort", 3, "yangchang");
        System.out.println("jedisSetDemo after zincrby 3 yangchang values: " + resul2);

        long result3 = jedis.zcount("mysetsort", 50, 120);
        System.out.println("jedisSetDemo after zcount 50-120 values: " + result3);
        jedis.close();
    }
}
