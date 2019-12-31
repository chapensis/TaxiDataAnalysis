package pers.yangchang.TaxiDataAnalysis.redis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisListTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        jedisListDemo();
    }

    /**
     * 普通方式进行jedis操作
     */
    public static void jedisListDemo() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.lpush("mylist", "yangchang", "liyang");
        List<String> values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after lpush values: " + values);

        jedis.rpush("mylist", "chenjieying", "mahuiying");
        values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after rpush values: " + values);

        jedis.rpop("mylist");
        values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after rpop values: " + values);

        long length = jedis.llen("mylist");
        System.out.println("jedisListDemo after llen values: " + length);

        jedis.lpushx("mylist", "huangzaiyang");
        values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after lpushx values: " + values);

        // count > 0,删除count个元素, count < 0,从后面删除-count个元素
        // count = 0 ,删除所有指定的元素
        jedis.lrem("mylist", 2, "liyang");
        values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after lrem liyang values: " + values);

        jedis.lset("mylist", 0, "tiancaiyang");
        values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after lset values: " + values);

        jedis.linsert("mylist", BinaryClient.LIST_POSITION.BEFORE, "yangchang", "dalaopo");
        jedis.linsert("mylist", BinaryClient.LIST_POSITION.AFTER, "yangchang", "xiaolaopo");
        values = jedis.lrange("mylist", 0, -1);
        System.out.println("jedisListDemo after linsert values: " + values);
        jedis.close();
    }
}
