//package com.hobbymatcher.config.redis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//@Configuration
//@EnableRedisHttpSession
//public class RedisConfig {
//
//	@Bean
//	public LettuceConnectionFactory connectionFactory() {
//		return new LettuceConnectionFactory();
//	}
//
////    @Bean
////    public RedisConnectionFactory redisConnectionFactory() {
////        return new JedisConnectionFactory();
////    }
//
//	@Bean
//	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//		return new StringRedisTemplate(connectionFactory);
//	}
//}
