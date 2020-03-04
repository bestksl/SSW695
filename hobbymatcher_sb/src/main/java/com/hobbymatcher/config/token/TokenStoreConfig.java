package com.hobbymatcher.config.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author HaoxuanLi
 * @version 1.0
 * @date 2020/3/3 19:42
 */
@Configuration
public class TokenStoreConfig {

    @Qualifier("connectionFactory")
    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(lettuceConnectionFactory);
    }
}
