package com.yangchang.TaxiDataAnalysis.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangchang
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private int database;
    private String host;
    private int port;
    /**
     * 秒
     */
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    /**
     * 秒
     */
    private int poolMaxWait;
}
