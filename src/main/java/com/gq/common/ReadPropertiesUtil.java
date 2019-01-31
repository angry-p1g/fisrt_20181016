package com.gq.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jedis") //该后缀可以不用配置
public class ReadPropertiesUtil {
    @Value("${jedis.pool.host}")
    private String host;

    @Value("${jedis.pool.port}")
    private int port;

    @Value("${jedis.pool.config.maxTotal}")
    private int maxTotal;

    @Value("${jedis.pool.config.maxIdle}")
    private int maxIdle;

    @Value("${jedis.pool.config.maxWaitMillis}")
    private int maxWaitMillis;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getHost() {

        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
