package pers.yangchang.TaxiDataAnalysis.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisKeysTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        jedisKeysDemo();
    }

    public static void jedisKeysDemo() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Set<String> keys = jedis.keys("*");
        System.out.println("jedisSetDemo after keys values: " + keys);

        jedis.del("mysetsort");
        keys = jedis.keys("*");
        System.out.println("jedisSetDemo after del keys values: " + keys);

        jedis.expire("myset", 60);
        long time = jedis.ttl("myset");
        System.out.println("jedisSetDemo after ttl values: " + time);

        String type = jedis.type("num");
        System.out.println("jedisSetDemo after type of num values: " + type);
        jedis.close();
    }
}
