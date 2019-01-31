package com.gq.redis_pool;

import com.gq.common.ReadPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Redis连接池工具类（自己玩的）
 * 选择Config的作用 (@ConditionalOnClass)
 */
@Component
//@EnableConfigurationProperties(ReadPropertiesUtil.class)(重复加载bean)
//@ConditionalOnClass(RedisClient.class)
public class JedisConfig {
    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);
    @Autowired
    ReadPropertiesUtil prop;

//    private static ThreadLocal<Jedis> local = new ThreadLocal<Jedis>();  //ThreadLocal的作用与缺点

    @Bean(name = "jedisPool")
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(prop.getMaxTotal());
        config.setMaxIdle(prop.getMaxIdle());
        config.setMaxWaitMillis(prop.getMaxWaitMillis());
        return new JedisPool(config, prop.getHost(), prop.getPort());
    }

    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
        logger.info("初始化……Redis Client==Host={},Port={}", prop.getHost(), prop.getPort());
        RedisClient redisClient = new RedisClient();
        redisClient.setJedisPool(pool);
        return redisClient;
    }

}
